package uz.nlg.mega.screens.bottom

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import uz.nlg.mega.R
import uz.nlg.mega.model.Category
import uz.nlg.mega.model.ProductsScreenState
import uz.nlg.mega.model.Subcategory
import uz.nlg.mega.mvvm.ProductsViewModel
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MostSoldProducts
import uz.nlg.mega.utils.ProductSearchType
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.views.CategoryItem
import uz.nlg.mega.views.CategoryTopItem
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.ProductItem
import uz.nlg.mega.views.SearchAndFilterTopSection


val productsScreenState = mutableStateOf(ProductsScreenState(false, null, ProductSearchType.None))


@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    BackHandler {
        viewModel.onBackPressed(activity)
    }

    if (viewModel.isGoLogin.value) {
        navigateToLoginScreen(LocalContext.current)
    }

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
        viewModel.errorMessage.value = null
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var isSearching by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color_F6)
    ) {
        Column {
            SearchAndFilterTopSection(
                isBack = false,
                title = stringResource(id = R.string.str_products),
                onBackClick = {},
                onEditTextBackClick = {
                    searchText = ""
                }
            ) {
                searchText = it
                isSearching = searchText != ""
            }

            if (!productsScreenState.value.isCategorySectionState)
                SimpleProductsSection(viewModel = viewModel, searchText = searchText)
            else
                ProductsCategorySection(
                    viewModel = viewModel,
                    productsScreenState.value.category!!,
                    searchText
                )

        }

        if (viewModel.isLoading.value) LoadingView()
    }
}

@Composable
fun ProductsCategorySection(
    viewModel: ProductsViewModel,
    category: Category,
    searchText: String
) {
    Column {
        if (viewModel.topSubCategories.isNotEmpty()) LazyRow(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            state = rememberLazyListState(viewModel.topSubCategories.size)
        ) {
            items(viewModel.topSubCategories.size) {
                CategoryTopItem(
                    title = viewModel.topSubCategories[it].name,
                    isLast = it == viewModel.topSubCategories.size - 1
                )
            }
        }

        if (viewModel.subCategories.isNotEmpty()) LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .weight(1f),
            state = rememberLazyListState()
        ) {
            if (viewModel.isLoading.value) {
                item {
                    LoadingView()
                }
            } else {
                items(viewModel.subCategories.size) { id ->
                    CategoryItem(
                        category = viewModel.subCategories[id]
                    ) {
                        if (it.subcategoriesCount >= 1) {
                            viewModel.getSubCategoryProducts(
                                Subcategory(
                                    it.id,
                                    it.name,
                                    it.productsCount
                                )
                            )
                        }
                    }
                }
                item {
                    if (viewModel.subCategories.size >= 15) LaunchedEffect(true) {
                        viewModel.getSubCategory(category, false)
                    }
                }
            }
        }

        if (viewModel.subCategoryProducts.isNotEmpty()) LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .weight(1f),
            state = rememberLazyListState()
        ) {
            if (viewModel.isLoading.value) {
                item {
                    LoadingView()
                }
            } else {
                items(viewModel.subCategoryProducts.size) { index ->
                    ProductItem(
                        search = searchText,
                        product = viewModel.subCategoryProducts[index]
                    ) {}
                }
                item {
                    if (viewModel.subCategoryProducts.size >= 15) LaunchedEffect(true) {
//                    viewModel.getSubCategoryProducts()
                    }
                }
            }
        }

    }
}

@Composable
fun SimpleProductsSection(
    viewModel: ProductsViewModel,
    searchText: String
) {

    LaunchedEffect(true) {
        if (productsScreenState.value.productType == ProductSearchType.ByCategory)
            viewModel.getCategories()
        else
            viewModel.getProducts("")
    }

    var productType by remember {
        mutableStateOf(productsScreenState.value.productType)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MainButton(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(id = R.string.str_category),
                textColor = if (productType == ProductSearchType.ByCategory) Color.White else Color_66,
                textSize = 13.sp,
                isTextBold = false,
                backgroundColor = if (productType == ProductSearchType.ByCategory) MainColor else Color.White,
                strokeColor = if (productType == ProductSearchType.ByCategory) MainColor else Color_E8
            ) {
                productType = if (productType == ProductSearchType.ByCategory) {
                    viewModel.getProducts(searchText)
                    ProductSearchType.None
                } else {
                    viewModel.getCategories()
                    ProductSearchType.ByCategory
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            MainButton(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(id = R.string.str_more_sold),
                textColor = if (productType == ProductSearchType.ByMoreSold) Color.White else Color_66,
                textSize = 13.sp,
                isTextBold = false,
                backgroundColor = if (productType == ProductSearchType.ByMoreSold) MainColor else Color.White,
                strokeColor = if (productType == ProductSearchType.ByMoreSold) MainColor else Color_E8
            ) {
                productType = if (productType == ProductSearchType.ByMoreSold) {
                    viewModel.getProducts("", true)
                    ProductSearchType.None
                } else {
                    viewModel.getProducts("", true, MostSoldProducts)
                    ProductSearchType.ByMoreSold
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
        ) {
            if (productType == ProductSearchType.ByCategory) {
                if (viewModel.categories.isNotEmpty()) LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                        .weight(1f),
                    state = rememberLazyListState()
                ) {
                    if (viewModel.isLoading.value) {
                        item {
                            LoadingView()
                        }
                    } else {
                        items(viewModel.categories.size) { id ->
                            CategoryItem(
                                category = viewModel.categories[id]
                            ) {
                                if (it.subcategoriesCount >= 1) {
                                    viewModel.getSubCategory(it, true)
                                    productsScreenState.value =
                                        ProductsScreenState(true, it, ProductSearchType.ByCategory)
                                }
                            }
                        }
                        item {
                            if (viewModel.categories.size >= 15) LaunchedEffect(true) {
                                viewModel.getCategories()
                            }
                        }
                    }
                }
            } else if (viewModel.products.isNotEmpty()) LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .weight(1f),
                state = rememberLazyListState()
            ) {
                if (viewModel.isLoading.value) {
                    item {
                        LoadingView()
                    }
                } else {
                    items(viewModel.products.size) { index ->
                        ProductItem(
                            search = searchText,
                            product = viewModel.products[index]
                        ) {}
                    }
                    item {
                        if (viewModel.products.size >= 15) LaunchedEffect(true) {
                            viewModel.getProducts(searchText)
                        }
                    }
                }
            }
        }

    }
}