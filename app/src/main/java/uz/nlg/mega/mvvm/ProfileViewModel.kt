package uz.nlg.mega.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.ProfileRepository
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.model.Profile
import uz.nlg.mega.utils.AccessToken
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.RefreshToken
import uz.nlg.mega.utils.printError
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _loggedOut = mutableStateOf(false)
    val isLoggedOut = _loggedOut

    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _profile = mutableStateOf<Profile?>(null)
    val myProfile = _profile

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin

    fun logOut() = viewModelScope.launch {
        _loading.value = true

        try {

            val response = repository.logOut()

            if (response.isSuccessful) {
                securePrefs.saveString(AccessToken, null)
                securePrefs.saveString(RefreshToken, null)
                SharedPrefs(context).saveBoolean(IsSignedIn, false)

                _loggedOut.value = true
            } else if (response.code() == 401) {
                securePrefs.saveString(AccessToken, null)
                securePrefs.saveString(RefreshToken, null)
                SharedPrefs(context).saveBoolean(IsSignedIn, false)

                _goLogin.value = true
            } else {
                val errorResponse =
                    Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                _error.value = errorResponse.detail ?: "No Connection"
                _loading.value = false
            }
        } catch (e: HttpException) {
            _loading.value = false
            printError(e)
        } catch (e: Exception) {
            _loading.value = false
            printError(e)
        }

    }

    fun getProfileInformation() = viewModelScope.launch {
        _loading.value = true

        try {

            val response = repository.getProfile()

            if (response.isSuccessful) {
                _profile.value = response.body()!!
                _loading.value = false
            } else if (response.code() == 401) {
                _goLogin.value = true
            } else {
                val errorResponse =
                    Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                _error.value = errorResponse.detail ?: "No Connection"
                _loading.value = false
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