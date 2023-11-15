package uz.nlg.mega.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.DebtInformationRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.model.Payment
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject


@SuppressLint("StaticFieldLeak")
@HiltViewModel
class DebtInformationViewModel @Inject constructor(
    private val repository: DebtInformationRepository,
    private val refreshRepository: RefreshTokenRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {


    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin

    private var isNextAvailable = true
    private var page = 0

    private val _data = mutableStateListOf<Payment>()
    val data = _data


    fun getDebtByDayOrMonth(
        search: String,
        sellerId: String,
        from: String,
        to: String,
        isSearched: Boolean = false
    ) = viewModelScope.launch {

        if (isSearched) {
            isNextAvailable = true
            page = 0
        }

        if (isNextAvailable) {
            page++
            _loading.value = page == 1

            try {

                var isStillCalling = true

                while (isStillCalling) {

                    val handler = NetworkHandler(
                        repository.getDebtByDayOrMonth(
                            search = search,
                            sellerId = sellerId,
                            from = from,
                            to = to,
                            page = page
                        ),
                        ErrorResponse::class.java,
                    )

                    handler.handleSuccess {

                        isNextAvailable = it!!.next != null

                        if (isSearched) _data.clear()

                        _data.addAll(it.results)

                        isStillCalling = false
                        _loading.value = false
                    }

                    handler.handleFailure(401) {
                        _error.value = it?.detail ?: it?.error ?: it?.code ?: it?.message
                                ?: it?.phoneNumber?.get(0)
                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleRefreshToken(this) {

                        refreshToken(refreshRepository, securePrefs) { isRefreshed ->

                            if (isRefreshed) {
                                isStillCalling = true
                            } else {
                                isStillCalling = false
                                _error.value = SomethingWentWrong
                                SharedPrefs(context).saveBoolean(IsSignedIn, false)
                                _goLogin.value = true
                            }

                        }
                    }

                    handler.handleServerError {
                        _error.value = "$ServerError$it"
                        _loading.value = false
                        isStillCalling = false
                    }

                }

            } catch (e: HttpException) {
                _loading.value = false
                printError(e)
            } catch (e: Exception) {
                _loading.value = false
                printError(e)
            }
        }

    }
}