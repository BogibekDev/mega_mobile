package uz.nlg.mega.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.screens.destinations.AddProductScreenDestination
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.Customers
import uz.nlg.mega.utils.FilterType
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.CustomerFilterView
import uz.nlg.mega.views.ItemCustomer
import uz.nlg.mega.views.SearchAndFilterTopSection

@Destination
@Composable
fun AddCustomerScreen(
    navigator: DestinationsNavigator? = null,
    chequeId: Int
) {

    var searchText by remember {
        mutableStateOf("")
    }

    var isFilterOpen by remember {
        mutableStateOf(false)
    }

    var filterType by remember {
        mutableStateOf<FilterType>(FilterType.None)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchAndFilterTopSection(
                isBack = true,
                isFilter = true,
                title = stringResource(id = R.string.str_customers),
                onBackClick = {
                    navigator!!.navigateUp()
                },
                onFilterClick = {
                    isFilterOpen = !isFilterOpen
                }
            ) {
                searchText = it
            }

            LazyColumn {
                Customers.forEach {
                    item {
                        ItemCustomer(
                            searchText = searchText,
                            customer = it
                        )
                    }
                }
            }

        }

        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
                .padding(end = PADDING_VALUE)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopEnd
        ) {
            AnimatedVisibility(isFilterOpen) {
                CustomerFilterView(filterType = filterType) {
                    filterType = it
                    isFilterOpen = false
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
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
}

@Preview
@Composable
fun AddCustomerScreenPreview() {
    AddCustomerScreen(
        chequeId = 1
    )
}