package uz.nlg.mega.mvvm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.LoginRepository
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.AccessToken
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.ProfileName
import uz.nlg.mega.utils.RefreshToken
import uz.nlg.mega.utils.printError
import javax.inject.Inject


@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    val isSuccess = mutableStateOf<Boolean?>(null)

    private val _error = mutableStateOf("")
    val errorMessage = _error

    fun userLogin(username: String, password: String) = viewModelScope.launch {
        _loading.value = true
        try {
            val handler =
                NetworkHandler(repository.userLogin(username, password), ErrorResponse::class.java)

            handler.handleSuccess {
                securePrefs.saveString(AccessToken, it.access)
                securePrefs.saveString(RefreshToken, it.refresh)
                SharedPrefs(context).saveString(ProfileName, "${it.firstName} ${it.lastName}")

                Log.i("TOKEN", it.access)
                Log.i("TOKEN", it.refresh)

                SharedPrefs(context).saveBoolean(IsSignedIn, true)
                isSuccess.value = true
            }

            handler.handleFailure {
                _error.value = it.message ?: "No Connection"
                isSuccess.value = false
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