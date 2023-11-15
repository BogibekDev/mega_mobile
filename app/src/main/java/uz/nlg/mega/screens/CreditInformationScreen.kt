package uz.nlg.mega.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.mvvm.DebtInformationViewModel
import uz.nlg.mega.screens.destinations.CustomerInformationScreenDestination
import uz.nlg.mega.utils.CreditType
import uz.nlg.mega.utils.SellerId
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.CreditInformationItem
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.SearchAndFilterTopSection
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@Destination
@Composable
fun CreditInformationScreen(
    navigator: DestinationsNavigator? = null,
    type: CreditType,
    viewModel: DebtInformationViewModel = hiltViewModel()
) {

    var searchText by remember {
        mutableStateOf("")
    }

    var searchJob by remember {
        mutableStateOf<Job>(Job())
    }

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val calendar = Calendar.getInstance()
    val today: String = sdf.format(calendar.time)
    calendar.add(Calendar.MONTH, -1)
    val monthAgo: String = sdf.format(calendar.time)
    val sellerId = SharedPrefs(LocalContext.current).getString(SellerId)!!


    LaunchedEffect(searchText) {
        searchJob.cancel()
        searchJob = if (type == CreditType.Daily)
            viewModel.getDebtByDayOrMonth(searchText, sellerId, today, today, true)
        else
            viewModel.getDebtByDayOrMonth(searchText, sellerId, monthAgo, today, true)
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

            if (viewModel.isLoading.value) LoadingView()
            else LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState()
            ) {
                items(viewModel.data.size) { position ->
                    CreditInformationItem(
                        searchText = searchText,
                        payment = viewModel.data[position],
                        onItemClick = { client ->
                            navigator!!.screenNavigate(
                                CustomerInformationScreenDestination(
                                    customer = client,
                                    isEditable = false,
                                )
                            )
                        }
                    )
                }

                item {
                    if (viewModel.data.size >= 20) {
                        LaunchedEffect(true) {
                            if (type == CreditType.Daily)
                                viewModel.getDebtByDayOrMonth(searchText, sellerId, today, today)
                            else
                                viewModel.getDebtByDayOrMonth(searchText, sellerId, today, monthAgo)
                        }
                    }
                }
            }

        }
    }
}