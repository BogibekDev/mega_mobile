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
import uz.nlg.mega.data.repository.PaymentRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.Cart
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.PaymentNoEqualSum
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class PaymentViewModel
@Inject constructor(
    private val repository: PaymentRepository,
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

    private val _done = mutableStateOf(false)
    val isDone = _done

    fun saveCheque(cart: Cart, totalPrice: Long, currentPrice: Long) = viewModelScope.launch {

        if (totalPrice > currentPrice) {
            _error.value = PaymentNoEqualSum
            return@launch
        }

        _loading.value = true

        try {
            var isStillCalling = true
            while (isStillCalling) {

                val handler = NetworkHandler(repository.saveCheque(cart), ErrorResponse::class.java)

                handler.handleSuccess {
                    _done.value = true
                    isStillCalling = false
                }

                handler.handleFailure(401) {
                    _error.value = it!!.error
                    _loading.value = false
                    isStillCalling = false
                }

                handler.handleServerError {
                    _error.value = "$ServerError$it"
                    _loading.value = false
                    isStillCalling = false
                }

                handler.handleRefreshToken(this) {
                    refreshToken(refreshToken, securePrefs) {
                        if (it) {
                            isStillCalling = true
                        } else {
                            isStillCalling = false
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