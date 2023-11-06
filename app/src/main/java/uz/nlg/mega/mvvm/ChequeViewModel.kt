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
import uz.nlg.mega.data.repository.ChequeRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ChequeViewModel
@Inject constructor(
    private val repository: ChequeRepository,
    private val refreshRepository: RefreshTokenRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _data = mutableStateListOf<Cheque>()
    val data = _data

    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin

    private var isNextAvailable = true
    private var page = 0

    fun getCheques(status: String = "", isTypeChanged: Boolean = false) = viewModelScope.launch {

        if (isTypeChanged) {
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
                        repository.getCheques(status, page),
                        ErrorResponse::class.java,
                    )

                    handler.handleSuccess {

                        isNextAvailable = it.next != null

                        if (isTypeChanged) _data.clear()

                        _data.addAll(it.results)

                        isStillCalling = false
                        _loading.value = false
                    }

                    handler.handleFailure(401) {
                        _error.value = it.detail
                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleRefreshToken(this) {

                        refreshToken(refreshRepository, securePrefs) { isRefreshed ->

                            if (isRefreshed) {
                                isStillCalling = true
                            } else {
                                isStillCalling = false
                                _error.value = "Something went wrong"
                                SharedPrefs(context).saveBoolean(IsSignedIn, false)
                                _goLogin.value = true
                            }

                        }
                    }

                    handler.handleServerError {
                        _error.value = "Server error: $it"
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

    fun deleteChequeById(cheque: Cheque, status: String) = viewModelScope.launch {
        _loading.value = true

        try {

            var isStillCalling = true

            while (isStillCalling) {

                val handler = NetworkHandler(
                    repository.deleteCheque(cheque.id),
                    ErrorResponse::class.java,
                )

                handler.handleSuccess {
                    getCheques(status, true)
                    isStillCalling = false
                    _loading.value = false
                }

                handler.handleFailure(401) {
                    _error.value = it.error ?: it.detail ?: it.message ?: it.code
                    _loading.value = false
                    isStillCalling = false
                }

                handler.handleRefreshToken(this) {

                    refreshToken(refreshRepository, securePrefs) { isRefreshed ->

                        if (isRefreshed) {
                            isStillCalling = true
                        } else {
                            isStillCalling = false
                            _error.value = "Something went wrong"
                            SharedPrefs(context).saveBoolean(IsSignedIn, false)
                            _goLogin.value = true
                        }

                    }
                }

                handler.handleServerError {
                    _error.value = "Server error: $it"
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