package uz.nlg.mega.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Job
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.ProductsViewModel
import uz.nlg.mega.screens.bottom.ProductsCategorySection
import uz.nlg.mega.screens.bottom.SimpleProductsSection
import uz.nlg.mega.screens.bottom.productsScreenState
import uz.nlg.mega.screens.destinations.EnterQuantityScreenDestination
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.SearchAndFilterTopSection

@Destination
@Composable
fun AddProductScreen(
    navigator: DestinationsNavigator,
    viewModel: ProductsViewModel = hiltViewModel()
) {

    BackHandler {
        viewModel.onBackPressed {
            navigator.navigateUp()
        }
    }

    var searchJob by remember {
        mutableStateOf<Job>(Job())
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

    var isFirst by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(searchText) {
        if (isFirst) {
            isFirst = false
        } else {
            searchJob.cancel()
            searchJob = viewModel.getProducts(searchText, true)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color_F6)
    ) {
        Column {
            SearchAndFilterTopSection(
                isBack = true,
                title = stringResource(id = R.string.str_products),
                onBackClick = {
                    navigator.navigateUp()
                },
                onEditTextBackClick = {
                    searchText = ""
                },
                onEditTextClearClick = {
                    searchText = ""
                    isSearching = false
                }
            ) {
                searchText = it
                isSearching = searchText != ""
            }

            if (productsScreenState.value.isCategorySectionState)
                ProductsCategorySection(
                    viewModel = viewModel,
                    productsScreenState.value.category!!,
                    searchText
                ) {
                    navigator.screenNavigate(EnterQuantityScreenDestination(it))
                }
            else
                SimpleProductsSection(viewModel = viewModel, searchText = searchText) {
                    navigator.screenNavigate(EnterQuantityScreenDestination(it))
                }

        }

        if (viewModel.isLoading.value) LoadingView()
    }
}