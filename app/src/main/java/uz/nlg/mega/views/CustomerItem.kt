package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import uz.nlg.mega.model.SearchedClient
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.forSearchText
import uz.nlg.mega.utils.moneyType

@Composable
fun CustomerItem(
    searchText: String = "",
    customer: SearchedClient,
    onItemClick: (customer: SearchedClient) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(customer)
            }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PADDING_VALUE)
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${customer.firstName} ${customer.lastName}".forSearchText(searchText),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        color = ItemTextColor
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = customer.phoneNumber,
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color_66
                    )
                }

                if (customer.balance != 0L)
                    Text(
                        text = customer.balance.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = if (customer.balance > 0L) GreenColor else RedTextColor
                    )

            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color_E8)
            )
        }
    }
}