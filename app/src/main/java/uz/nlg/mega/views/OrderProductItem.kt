package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import uz.nlg.mega.R
import uz.nlg.mega.model.Product
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.moneyType

@Composable
fun OrderProductItem(
    product: Product
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(69.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = product.name,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = ItemTextColor
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    modifier = Modifier,
                    text = "${product.quantity} ${product.firstType}",
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = ItemTextColor
                )

                Text(
                    modifier = Modifier,
                    text = (product.price * product.quantity).moneyType(),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = ItemTextColor
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color_E8)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun OrderProductItemPreview() {

    var arrayList = remember {
        mutableStateOf(
            listOf(
                Product(
                    id = 1,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 2,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 3,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 4,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 5,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 6,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 7,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 8,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 9,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                ), Product(
                    id = 10,
                    name = "Sayding L-Brus-15x240",
                    quantity = 2,
                    firstType = "pachka",
                    secondType = "dona",
                    price = 20_000
                )
            )
        )
    }

    LazyColumn {
        items(items = arrayList.value, key = { item: Product ->
            item.id
        }) { item: Product ->
            RevealSwipe(
                modifier = Modifier,
                directions = setOf(
                    RevealDirection.EndToStart
                ),
                hiddenContentEnd = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 20.dp),
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                onBackgroundEndClick = {

                },
                backgroundCardEndColor = RedTextColor,
                animateBackgroundCardColor = true,
                shape = RoundedCornerShape(0.dp)
            ) {
                OrderProductItem(product = item)
            }
        }
    }
}