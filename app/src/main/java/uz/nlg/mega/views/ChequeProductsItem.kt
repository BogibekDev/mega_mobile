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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.model.Product
import uz.nlg.mega.ui.theme.Color_11
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.moneyType

@Composable
fun ChequeProductsItem(
    product: Product
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = product.name,
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            color = Color_66,
                            maxLines = 1
                        )
                        Text(
                            text = "${product.quantity} x ${product.price}",
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            color = Color_66,
                            maxLines = 1
                        )
                    }

                    Text(
                        text = "= ${(product.price * product.quantity).moneyType()}",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color_11,
                        maxLines = 1
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

@Preview(showBackground = true)
@Composable
fun ChequeProductsItemPreview() {
    ChequeProductsItem(
        product = OrderProducts[0]
    )
}