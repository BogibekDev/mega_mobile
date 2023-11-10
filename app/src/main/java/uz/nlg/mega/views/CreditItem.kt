package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.ui.theme.Color_11
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.dateToString
import uz.nlg.mega.utils.moneyType

@Composable
fun CreditItem(
    cheque: Cheque
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "№ ${cheque.serialNumber}",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color_66
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = cheque.client?.firstName?:"",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color_11
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = cheque.chequeSum.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = if (cheque.chequeSum > 0L) GreenColor else RedTextColor
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = dateToString(cheque.createdAt),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color_66
                    )
                }

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color_E8)
            )
        }
    }

}