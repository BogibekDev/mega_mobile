package uz.nlg.mega.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.data.repository.AddCustomerRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.Client
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
class AddCustomerViewModel
@Inject constructor(
    private val repository: AddCustomerRepository,
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

    private val _data = mutableStateListOf<Client>()
    val data = _data

    private var isNextAvailable = true
    private var page = 0

    fun getCustomers(search: String, isSearched: Boolean = false, ordering: String?) =
        viewModelScope.launch {
            if (isSearched) {
                isNextAvailable = true
                page = 0
            }

            if (isNextAvailable) {
                page++
                _loading.value = page == 1

                try {

                    var isStillCalling = true
                    while (isStillCalling) {

                        val handler = NetworkHandler(
                            repository.getCustomers(search, ordering, page),
                            ErrorResponse::class.java
                        )

                        handler.handleSuccess {

                            isNextAvailable = it!!.next != null

                            if (isSearched) _data.clear()

                            _data.addAll(it.results)

                            _loading.value = false
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


    private val _added = mutableStateOf(false)
    val isAdded = _added

    fun addCustomerToCheque(id: Int) = viewModelScope.launch {
        _loading.value = true

        try {
            var isStillCalling = true
            while (isStillCalling) {

                val handler = NetworkHandler(repository.addCustomerToCart(id), ErrorResponse::class.java)

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