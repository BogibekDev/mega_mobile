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
import uz.nlg.mega.data.repository.ProductsRepository
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.Category
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.model.Product
import uz.nlg.mega.model.Subcategory
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

    private var _products = mutableStateListOf<Product>()
    val products = _products

    private var isProductsNextAvailable = true
    private var productPage = 0

    private var mainCategories = mutableStateListOf<Category>()

    private var _categories = mutableStateListOf<Category>()
    val categories = _categories

    private val _subCategories = mutableStateListOf<Subcategory>()
    val subCategories = _subCategories

    private var isCategoriesNextAvailable = true
    private var categoryPage = 0

    fun getProducts(search: String, isTypeChanged: Boolean = false, ordering: String = "") = viewModelScope.launch {
        if (isTypeChanged) {
            isProductsNextAvailable = true
            productPage = 0
        }
        if (isProductsNextAvailable) {
            productPage++
            _loading.value = productPage == 1

            try {

                var isStillCalling = true
                while (isStillCalling) {

                    val handler = NetworkHandler(repository.getProducts(search, productPage, ordering), ErrorResponse::class.java)

                    handler.handleSuccess {

                        isProductsNextAvailable = it.next != null

                        if (isTypeChanged) _products.clear()

                        _products.addAll(it.results)

                        _categories.clear()
                        mainCategories.clear()
                        isCategoriesNextAvailable = true
                        categoryPage = 0

                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleFailure(401) {
                        _error.value = it.error
                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleServerError {
                        _error.value = "Server error: $it"
                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleRefreshToken(this) {
                        refreshToken(refreshRepository, securePrefs) {
                            if (it) {
                                isStillCalling = true
                            } else {
                                isStillCalling = false
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

    fun getCategories() = viewModelScope.launch {
        if (isCategoriesNextAvailable) {
            categoryPage++
            _loading.value = categoryPage == 1

            try {

                var isTrue = true
                while (isTrue) {

                    val handler = NetworkHandler(repository.getCategories(categoryPage), ErrorResponse::class.java)

                    handler.handleSuccess {

                        isCategoriesNextAvailable = it.next != null

                        _categories.addAll(it.results)
                        mainCategories.addAll(it.results)

                        _products.clear()
                        isProductsNextAvailable = true
                        productPage = 0

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

    fun getSubCategory(id: Int) = viewModelScope.launch {
        _loading.value = true

        _subCategories.clear()

        val category = categories.find { it.id == id }!!

        _subCategories.add(Subcategory(0, "Kategoriyalar"))
        _subCategories.add(Subcategory(category.id, category.name))

        _categories.clear()

        val tempCategories: ArrayList<Category> = arrayListOf()
        for (i in category.subcategories) tempCategories.add(Category(i.id, i.name, 0, emptyList(), 0))

        _categories.addAll(tempCategories)

        _loading.value = false
    }


}