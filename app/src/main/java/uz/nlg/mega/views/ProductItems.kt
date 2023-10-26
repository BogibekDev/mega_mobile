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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.model.Product
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_BD
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.forSearchText
import uz.nlg.mega.utils.moneyType

@Composable
fun AddingProductItem(
    search: String,
    product: Product,
    onItemClick: (product: Product) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(product)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.name.forSearchText(search),
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = ItemTextColor
            )

            Text(
                text = "${product.quantity} ${product.firstType}",
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color_66
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color_E8)
        )

    }
}

@Composable
fun ProductItem(
    search: String,
    product: Product,
    onItemClick: (product: Product) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(product)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.name.forSearchText(search),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = ItemTextColor
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "${stringResource(id = R.string.str_enter_price)}: ${product.price.moneyType()}",
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color_BD
                )

            }

            Text(
                text = "${product.quantity} ${product.firstType}",
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color_66
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color_E8)
        )

    }
}

@Preview
@Composable
fun AddingProductItemPreview() {
    LazyColumn {
        for (i in 1..30) {
            item {
                AddingProductItem(
                    search = "",
                    product = Product(
                        id = 1,
                        name = "Sayding L-Brus-15x240",
                        quantity = 55,
                        firstType = "dona",
                        secondType = "pachka",
                        price = 10_000
                    )
                ) {

                }
            }
        }
    }
}