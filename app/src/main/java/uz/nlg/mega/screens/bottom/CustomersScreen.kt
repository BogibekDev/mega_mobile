package uz.nlg.mega.screens.bottom

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Job
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.SearchClientViewModel
import uz.nlg.mega.screens.destinations.CustomerInformationScreenDestination
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.FilterType
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.CustomerItem
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.MoneyFilterView
import uz.nlg.mega.views.SearchAndFilterTopSection

@Composable
fun CustomersScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: SearchClientViewModel = hiltViewModel()
) {

    var searchText by remember {
        mutableStateOf("")
    }
    var searchJob by remember {
        mutableStateOf<Job>(Job())
    }

    var isFilterOpen by remember {
        mutableStateOf(false)
    }

    var filterType by remember {
        mutableStateOf<FilterType>(FilterType.None)
    }


    LaunchedEffect(filterType.status) {
        searchJob.cancel()
        searchJob = viewModel.searchClient(
            search = searchText,
            isSearched = true,
            isDebt = filterType.status
        )
    }

    LaunchedEffect(searchText) {
        searchJob.cancel()
        searchJob = viewModel.searchClient(
            search = searchText,
            isSearched = true,
            isDebt = filterType.status
        )
    }

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
        viewModel.errorMessage.value = null
    }

    if (viewModel.isGoLogin.value) navigateToLoginScreen(LocalContext.current)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            SearchAndFilterTopSection(
                isBack = false,
                isFilter = true,
                title = stringResource(id = R.string.str_customers),
                onBackClick = {
                },
                onFilterClick = {
                    isFilterOpen = !isFilterOpen
                }
            ) {
                searchText = it
            }


            if (viewModel.isLoading.value) LoadingView()
            else if (viewModel.data.isNotEmpty())
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState()
                ) {
                    items(viewModel.data.size - 1) { position ->
                        CustomerItem(
                            searchText = searchText,
                            customer = viewModel.data[position],
                            onItemClick = {
                                navigator!!.screenNavigate(
                                    CustomerInformationScreenDestination(
                                        customer = it
                                    )
                                )
                            }
                        )
                    }
                    item {
                        CustomerItem(
                            searchText = searchText,
                            customer = viewModel.data.last(),
                            onItemClick = {
                                navigator!!.screenNavigate(
                                    CustomerInformationScreenDestination(
                                        customer = it
                                    )
                                )
                            }
                        )

                        if (viewModel.data.size >= 20) LaunchedEffect(true) {
                            viewModel.searchClient(search = searchText)
                        }
                    }

                }


        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .padding(end = PADDING_VALUE)
            .background(Color.Transparent),
        contentAlignment = Alignment.TopEnd
    ) {
        AnimatedVisibility(isFilterOpen) {
            MoneyFilterView(filterType = filterType) {
                filterType = it
                isFilterOpen = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .padding(end = 38.dp)
                .padding(bottom = 38.dp)
                .padding(top = 20.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = CircleShape,
                    spotColor = Color.Gray
                )
                .size(52.dp, 52.dp)
                .clip(CircleShape)
                .background(MainColor)
                .clickable {
                    navigator!!.screenNavigate(CustomerInformationScreenDestination(customer = null))
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp, 24.dp),
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

}