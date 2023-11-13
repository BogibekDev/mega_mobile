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
import uz.nlg.mega.model.ProductsScreenState
import uz.nlg.mega.model.Subcategory
import uz.nlg.mega.screens.bottom.productsScreenState
import uz.nlg.mega.utils.IsSignedIn
import uz.nlg.mega.utils.NetworkHandler
import uz.nlg.mega.utils.ProductSearchType
import uz.nlg.mega.utils.ServerError
import uz.nlg.mega.utils.SomethingWentWrong
import uz.nlg.mega.utils.TopFirstSubCategory
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
    private var mainProducts = mutableStateListOf<Product>()

    private var _categories = mutableStateListOf<Category>()
    val categories = _categories

    private val _topSubCategories = mutableStateListOf(TopFirstSubCategory)
    val topSubCategories = _topSubCategories

    private val _subCategories = mutableStateListOf<Category>()
    val subCategories = _subCategories

    private val _subCategoryProducts = mutableStateListOf<Product>()
    val subCategoryProducts = _subCategoryProducts

    private var isCategoriesNextAvailable = true
    private var categoryPage = 0

    private var isSubcategoriesNextAvailable = true
    private var subcategoryPage = 0

    private var isSubcategoryProductsNextAvailable = true
    private var subcategoryProductsPage = 0

    fun getProducts(search: String, isTypeChanged: Boolean = false, ordering: String = "") =
        viewModelScope.launch {
            if (isTypeChanged) {
                isProductsNextAvailable = true
                productPage = 0

                isCategoriesNextAvailable = true
                categoryPage = 0

                productsScreenState.value = ProductsScreenState(false, null, ProductSearchType.None)
            }
            if (isProductsNextAvailable) {
                productPage++
                _loading.value = productPage == 1

                try {

                    var isStillCalling = true
                    while (isStillCalling) {

                        val handler = NetworkHandler(
                            repository.getProducts(search, productPage, ordering),
                            ErrorResponse::class.java
                        )

                        handler.handleSuccess {

                            isProductsNextAvailable = it!!.next != null

                            if (isTypeChanged) {
                                _products.clear()
                                mainProducts.clear()
                            }

                            _categories.clear()
                            mainCategories.clear()

                            _products.addAll(it.results)
                            mainProducts.addAll(it.results)

                            isCategoriesNextAvailable = true
                            categoryPage = 0

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
                            refreshToken(refreshRepository, securePrefs) {
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

    fun getCategories() = viewModelScope.launch {
        if (isCategoriesNextAvailable) {
            categoryPage++
            _loading.value = categoryPage == 1

            try {

                var isStillCalling = true
                while (isStillCalling) {

                    val handler = NetworkHandler(
                        repository.getCategories(categoryPage),
                        ErrorResponse::class.java
                    )

                    handler.handleSuccess {

                        isCategoriesNextAvailable = it!!.next != null

                        _categories.addAll(it.results)
                        mainCategories.addAll(it.results)

                        _products.clear()
                        mainProducts.clear()

                        isProductsNextAvailable = true
                        productPage = 0

                        _loading.value = false
                        isStillCalling = false
                    }

                    handler.handleFailure {
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
                        refreshToken(refreshRepository, securePrefs) {
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

    fun getSubCategory(category: Category, isClear: Boolean = false) = viewModelScope.launch {

        isSubcategoryProductsNextAvailable = true
        subcategoryProductsPage = 0
        _subCategoryProducts.clear()

        if (isClear) {
            _loading.value = productPage == 1
            _subCategories.clear()
            _topSubCategories.clear()
            _topSubCategories.add(TopFirstSubCategory)
        }

        if (isSubcategoriesNextAvailable) {
            subcategoryPage++
            _loading.value = productPage == 1

            try {

                var isStillCalling = true
                while (isStillCalling) {

                    val handler = NetworkHandler(
                        repository.getSubcategories(category.id),
                        ErrorResponse::class.java
                    )

                    handler.handleSuccess {

                        isSubcategoriesNextAvailable = it!!.next != null

                        _topSubCategories.add(
                            Subcategory(
                                category.id,
                                category.name,
                                category.productsCount
                            )
                        )
                        for (i in it.results) {
                            _subCategories.add(
                                Category(
                                    i.id,
                                    i.name,
                                    i.productsCount,
                                    i.productsCount
                                )
                            )
                        }

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
                        refreshToken(refreshRepository, securePrefs) {
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

    fun getSubCategoryProducts(subCategory: Subcategory) = viewModelScope.launch {

        _loading.value = productPage == 1
        isSubcategoriesNextAvailable = true
        subcategoryPage = 0
        _subCategories.clear()

        if (isSubcategoryProductsNextAvailable) {
            subcategoryProductsPage++
            _loading.value = productPage == 1

            try {

                var isStillCalling = true
                while (isStillCalling) {

                    val handler = NetworkHandler(
                        repository.getProductsBySubcategory(subCategory.id),
                        ErrorResponse::class.java
                    )

                    handler.handleSuccess {

                        isSubcategoryProductsNextAvailable = it!!.next != null

                        _topSubCategories.add(subCategory)
                        _subCategoryProducts.addAll(it.results)

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
                        refreshToken(refreshRepository, securePrefs) {
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

    fun onBackPressed(onBackPressed: () -> Unit) = viewModelScope.launch {

        _loading.value = true

        val lastAction = topSubCategories.size - 1
        _topSubCategories.removeLast()

        isSubcategoriesNextAvailable = true
        subcategoryPage = 0

        when (lastAction) {
            1 -> {
                productsScreenState.value =
                    ProductsScreenState(false, null, ProductSearchType.ByCategory)
            }

            2 -> {
                val lastData = topSubCategories.last()
                getSubCategory(
                    Category(
                        lastData.id,
                        lastData.name,
                        lastData.productsCount,
                        lastData.productsCount
                    ), true
                )
            }

            else -> {
                onBackPressed.invoke()
            }
        }

        _loading.value = false

    }

}