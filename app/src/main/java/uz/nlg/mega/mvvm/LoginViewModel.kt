package uz.nlg.mega.mvvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.repository.LoginRepository
import uz.nlg.mega.utils.printError
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _success = mutableStateOf<Boolean?>(null)

    fun userLogin(username: String, password: String) = viewModelScope.launch {
        _loading.value = true

        try {
            _loading.value = false

        } catch (e: HttpException) {
            _loading.value = false
            printError(e)
        }
    }

}