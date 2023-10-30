package uz.nlg.mega.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.screens.destinations.CustomerInformationScreenDestination
import uz.nlg.mega.utils.CreditType
import uz.nlg.mega.utils.Customers
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.CustomerItem
import uz.nlg.mega.views.SearchAndFilterTopSection

@Destination
@Composable
fun CreditInformationScreen(
    navigator: DestinationsNavigator? = null,
    type: CreditType
) {

    var searchText by remember {
        mutableStateOf("")
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            
            SearchAndFilterTopSection(
                isBack = true,
                title = if (type == CreditType.Daily) {
                    stringResource(id = R.string.str_credit_in_a_day)
                } else {
                    stringResource(id = R.string.str_credit_in_a_month)
                },
                onBackClick = {
                    navigator!!.navigateUp()
                }
            ) {
                searchText = it
            }

            LazyColumn {
                for (i in Customers) {
                    item {
                        CustomerItem(
                            searchText = searchText,
                            customer = i
                        ) {
                            navigator!!.screenNavigate(CustomerInformationScreenDestination(customer = it))
                        }
                    }
                }
            }
            
        }
    }
}