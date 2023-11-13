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
import uz.nlg.mega.data.repository.AddProductRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.CartAddProduct
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.AddProductPriceError
import uz.nlg.mega.utils.AddProductQuantityError
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.printError
import uz.nlg.mega.utils.refreshToken
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AddProductViewModel
@Inject constructor(
    private val repository: AddProductRepository,
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

    private val _added = mutableStateOf(false)
    val isAdded = _added

    fun addProduct(product: CartAddProduct) = viewModelScope.launch {

        if (product.quantity <= 0) {
            _error.value = AddProductQuantityError
            return@launch
        }

        if (product.soldPrice <= 0) {
            _error.value = AddProductPriceError
            return@launch
        }

        _loading.value = true

        try {
            var isStillCalling = true
            while (isStillCalling) {

                val handler = NetworkHandler(
                    repository.addProductToCheque(product),
                    ErrorResponse::class.java
                )

                handler.handleSuccess {
                    _added.value = true
                    isStillCalling = false
                }

                handler.handleFailure(401) {
                    _error.value = it!!.detail
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