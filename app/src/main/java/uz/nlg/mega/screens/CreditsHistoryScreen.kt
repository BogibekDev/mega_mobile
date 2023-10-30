package uz.nlg.mega.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uz.nlg.mega.R
import uz.nlg.mega.utils.Cheques
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.CreditItem

@Composable
fun CreditsHistoryScreen(

) {


    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            BackTopSection(title = stringResource(R.string.str_credit_history)) {
                //navigator!!.navigateUp()
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = PADDING_VALUE)

            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(Cheques.size) { position ->
                        CreditItem(cheque = Cheques[position])
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CreditsHistoryScreenPreview() {
    CreditsHistoryScreen()
}