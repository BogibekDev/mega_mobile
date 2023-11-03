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
import uz.nlg.mega.data.repository.ProductsRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository,
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

    fun getProducts(search: String) = viewModelScope.launch {
        _loading.value = true

        try {

            var isTrue = true
            while (isTrue) {

                val handler = NetworkHandler(repository.getProducts(), ErrorResponse::class.java)

                handler.handleSuccess {

                    _loading.value = false
                    isTrue = false
                }

                handler.handleFailure {
                    _error.value = it.detail
                    _loading.value = false
                    isTrue = false
                }

                handler.handleServerError {
                    _error.value = "Server error: $it"
                    _loading.value = false
                    isTrue = false
                }

                handler.handleRefreshToken(this) {
                    refreshToken(refreshRepository, securePrefs) {
                        if (it) {
                            isTrue = true
                        } else {
                            isTrue = false
                            _error.value = "Something went wrong"
                            SharedPrefs(context).saveBoolean(IsSignedIn, false)
                            _goLogin.value = true
                        }
                    }
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