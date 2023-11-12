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
import uz.nlg.mega.data.repository.OrderRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.CartResponse
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository,
    private val refreshToken: RefreshTokenRepository,
    private val securePrefs: SecurePrefs,
    private val context: Context
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin

    private val _data = mutableStateOf(CartResponse(null, 0L, arrayListOf()))
    val data = _data

    fun getCart() = viewModelScope.launch {
        _loading.value = true

        try {
            var isTrue = true
            while (isTrue) {

                val handler = NetworkHandler(repository.getCart(), ErrorResponse::class.java)

                handler.handleSuccess {
                    _data.value.cartItems.clear()
                    _data.value = it!!
                    _loading.value = false
                    isTrue = false
                }

                handler.handleFailure(401) {
                    _error.value = it!!.detail
                    _loading.value = false
                    isTrue = false
                }

                handler.handleServerError {
                    _error.value = "$ServerError$it"
                    _loading.value = false
                    isTrue = false
                }

                handler.handleRefreshToken(this) {
                    refreshToken(refreshToken, securePrefs) {
                        if (it) {
                            isTrue = true
                        } else {
                            isTrue = false
                            _error.value = SomethingWentWrong
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

    fun deleteChequeItem(id: Int) = viewModelScope.launch {
        _loading.value = true

        try {
            var isTrue = true
            while (isTrue) {

                val handler = NetworkHandler(repository.deleteItem(id), ErrorResponse::class.java)

                handler.handleSuccess() {
                    getCart()
                    isTrue = false
                }

                handler.handleFailure(401) {
                    _error.value = it!!.detail
                    _loading.value = false
                    isTrue = false
                }

                handler.handleServerError {
                    _error.value = "$ServerError$it"
                    _loading.value = false
                    isTrue = false
                }

                handler.handleRefreshToken(this) {
                    refreshToken(refreshToken, securePrefs) {
                        if (it) {
                            isTrue = true
                        } else {
                            isTrue = false
                            _error.value = SomethingWentWrong
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