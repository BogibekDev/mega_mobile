package uz.nlg.mega.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.ui.theme.Color_E6
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.MainButton

@Destination
@Composable
fun PaymentScreen(
    navigator: DestinationsNavigator? = null,
    cheque: Cheque
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 140.dp)
        ) {
            BackTopSection(title = stringResource(id = R.string.str_pay)) {
                navigator!!.navigateUp()
            }



        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(Color_E6)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = PADDING_VALUE)
                        .padding(horizontal = PADDING_VALUE),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.str_total),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Text(
                        text = cheque.totalPrice.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(PADDING_VALUE))

                MainButton(
                    modifier = Modifier
                        .padding(horizontal = PADDING_VALUE)
                        .padding(bottom = PADDING_VALUE),
                    text = stringResource(id = R.string.str_pay),
                    textColor = Color.White,
                    textSize = 16.sp,
                    backgroundColor = MainColor,
                    strokeColor = MainColor
                ) {

                }
            }
        }

    }
}

@Preview
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        cheque = Cheque(
            type = ChequeType.Paid,
            serialNumber = 12345,
            clientName = "Ogabek Matyakubov",
            date = "15.10.2023",
            time = "15:23",
            products = OrderProducts,
            totalPrice = 1_500_000
        )
    )
}
