package uz.nlg.mega.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.ChequeDetailRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.ChequeDetailResponse
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.NoInternetError
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ChequeDetailViewModel
@Inject constructor(
    private val repository: ChequeDetailRepository,
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


    private val _data = mutableStateOf<ChequeDetailResponse?>(null)
    val data = _data

    fun getChequeDetail(id: Int) = viewModelScope.launch {

        try {

            var isStillCalling = true

            while (isStillCalling) {

                val handler = NetworkHandler(
                    repository.getChequeById(id),
                    ErrorResponse::class.java,
                )

                handler.handleSuccess {

                    _data.value = it
                    isStillCalling = false
                    _loading.value = false
                }

                handler.handleFailure(401) {
                    _error.value = it!!.error ?: it.detail ?: it.message ?: it.code ?: NoInternetError
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