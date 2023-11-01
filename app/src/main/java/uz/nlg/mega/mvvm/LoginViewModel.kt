package uz.nlg.mega.mvvm

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.LoginRepository
import uz.nlg.mega.utils.AccessToken
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.RefreshToken
import uz.nlg.mega.utils.printError
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
): ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _success = mutableStateOf<Boolean?>(null)
    val isSuccess = _success

    private val _error = mutableStateOf("")
    val errorMessage = _error

    fun userLogin(username: String, password: String) = viewModelScope.launch {
        _loading.value = true
        try {
            val response = repository.userLogin(username, password)

            if (response.isSuccessful) {
                securePrefs.saveString(AccessToken, response.body()?.access!!)
                securePrefs.saveString(RefreshToken, response.body()?.refresh!!)
                SharedPrefs(context).saveBoolean(IsSignedIn, true)

                _success.value = true
            } else {
                _error.value = response.body()?.message!!

                _success.value = false
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