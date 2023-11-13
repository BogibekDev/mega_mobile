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
import uz.nlg.mega.model.Cart
import uz.nlg.mega.model.CartAddProduct
import uz.nlg.mega.model.CartResponse
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.OrderCustomerNullError
import uz.nlg.mega.utils.OrderNoProductError
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

    private val _cart = mutableStateOf<Cart?>(null)
    val cart = _cart

    fun getCart() = viewModelScope.launch {
        _loading.value = true

        try {
            var isStillCalling = true
            while (isStillCalling) {

                val handler = NetworkHandler(repository.getCart(), ErrorResponse::class.java)

                handler.handleSuccess {
                    _data.value.cartItems.clear()
                    _data.value = it!!
                    _loading.value = false
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

    fun deleteChequeItem(id: Int) = viewModelScope.launch {
        _loading.value = true

        try {
            var isStillCalling = true
            while (isStillCalling) {

                val handler = NetworkHandler(repository.deleteItem(id), ErrorResponse::class.java)

                handler.handleSuccess() {
                    getCart()
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

    fun saveCheque(type: ChequeType) = viewModelScope.launch {
        _loading.value = true

        if (data.value.cartItems.isEmpty()) {
            _error.value = OrderNoProductError
            _loading.value = false
        } else if (data.value.client == null) {
            _error.value = OrderCustomerNullError
            _loading.value = false
        } else {
            try {
                var isStillCalling = true
                while (isStillCalling) {

                    val products = ArrayList<CartAddProduct>()

                    for (it in data.value.cartItems) {
                        products.add(CartAddProduct(
                            it.product.id,
                            it.quantity,
                            it.quantityType,
                            it.soldPrice,
                            0
                        ))
                    }

                    _cart.value = Cart(
                        id = 0,
                        client = data.value.client?.id,
                        status = type.status,
                        chequeItems = products,
                        payments = arrayListOf()
                    )

                    val handler = NetworkHandler(repository.saveCheque(cart.value!!), ErrorResponse::class.java)

                    handler.handleSuccess {
                        getCart()
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

    private val _goPayment = mutableStateOf(false)
    val goPayment = _goPayment

    fun goPayment() = viewModelScope.launch {

        if (data.value.client == null) {
            _error.value = OrderCustomerNullError
            return@launch
        }

        if (data.value.cartItems.isEmpty()) {
            _error.value = OrderNoProductError
            return@launch
        }

        val products = ArrayList<CartAddProduct>()

        for (it in data.value.cartItems) {
            products.add(CartAddProduct(
                it.product.id,
                it.quantity,
                it.quantityType,
                it.soldPrice,
                0
            ))
        }

        _cart.value = Cart(
            id = 0,
            client = data.value.client?.id,
            status = "done",
            chequeItems = products,
            payments = arrayListOf()
        )

        _goPayment.value = true
    }

}