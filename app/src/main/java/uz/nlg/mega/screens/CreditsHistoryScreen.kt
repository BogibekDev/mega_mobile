package uz.nlg.mega.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.DebtHistoryViewModel
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.CreditItem
import uz.nlg.mega.views.LoadingView

@Destination
@Composable
fun CreditsHistoryScreen(
    navigator: DestinationsNavigator? = null,
    clientId: Int,
    viewModel: DebtHistoryViewModel = hiltViewModel()
) {


    LaunchedEffect(true) {
        viewModel.getDebtHistory(clientId.toString())
    }


    if (viewModel.isGoLogin.value) navigateToLoginScreen(LocalContext.current)

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
        viewModel.errorMessage.value = null
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            BackTopSection(title = stringResource(R.string.str_credit_history)) {
                navigator!!.navigateUp()
            }

            if (viewModel.isLoading.value) LoadingView()
            else Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = PADDING_VALUE)
                    .padding(bottom = PADDING_VALUE)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(viewModel.data.size) { position ->
                        CreditItem(payment = viewModel.data[position])
                    }
                    item {
                        if (viewModel.data.size >= 20) {
                            LaunchedEffect(true) {
                                viewModel.getDebtHistory(clientId.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditsHistoryScreenPreview() {
    CreditsHistoryScreen(clientId = 12)
}