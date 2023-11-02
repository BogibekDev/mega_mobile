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
import uz.nlg.mega.data.repository.ChequeRepository
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.model.Pagination
import uz.nlg.mega.utils.AccessToken
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.RefreshToken
import uz.nlg.mega.utils.printError
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ChequeViewModel
@Inject constructor(
    private val repository: ChequeRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _data = mutableStateOf<Pagination<Cheque>?>(null)
    val data = _data

    val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin

    fun getCheques(status: String = "", page: Int = 1, pageSize: Int = 15) = viewModelScope.launch {
        _loading.value = true

        try {
            val handler = NetworkHandler(
                repository.getCheques(status, page, pageSize),
                ErrorResponse::class.java
            )

            handler.handleSuccess {
                data.value = it
            }

            handler.handleByFailureStatusCode(401) {
                securePrefs.saveString(AccessToken, null)
                securePrefs.saveString(RefreshToken, null)
                SharedPrefs(context).saveBoolean(IsSignedIn, false)

                _goLogin.value = true
            }

            handler.handleFailure(401) {
                _error.value = it.detail ?: "No Connection"
            }

            handler.handleServerError {
                _error.value = "Server error: $it"
            }

            _loading.value = false
        } catch (e: HttpException) {
            _loading.value = false
            printError(e)
        } catch (e: Exception) {
            _loading.value = false
            printError(e)
        }

    }


}