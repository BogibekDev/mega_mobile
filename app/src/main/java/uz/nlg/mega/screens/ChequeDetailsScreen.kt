package uz.nlg.mega.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.utils.Cheques
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.views.BackTopSection

@Composable
fun ChequeDetailsScreen(
    cheque: Cheque
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column {
            BackTopSection(
                title = stringResource(R.string.str_about_cheque)
            ) {

            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = cheque.totalPrice.moneyType(),
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

        }
    }

}

@Preview
@Composable
fun ChequeDetailsScreenPreview() {
    ChequeDetailsScreen(
        cheque = Cheques[0]
    )
}