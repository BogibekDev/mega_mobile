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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_BD
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont

@Composable
fun CategoryTopItem(
    title: String,
    isLast: Boolean
) {

    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .padding(end = 8.dp)
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = if (isLast) MainColor else Color_BD
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            modifier = Modifier
                .padding(top = 3.dp)
                .size(6.dp, 10.dp),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = null,
            tint = if (isLast) MainColor else Color_BD
        )
    }

}

@Composable
fun CategoryItem(
    id: Int,
    title: String,
    quantity: Int,
    onItemClick: (categoryId: Int) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(id)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = ItemTextColor
            )

            Row() {
                Text(
                    text = "$quantity",
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color_66
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .size(6.dp, 10.dp),
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    tint = Color_66
                )
            }

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
fun CategoryItemsPreview() {
    Column {
        LazyRow(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            for (i in 0..0) {
                item {
                    CategoryTopItem(
                        title = "Kategoriyalar",
                        isLast = false
                    )
                }
            }
            item {
                CategoryTopItem(
                    title = "Kategoriyalar",
                    isLast = true
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
        ) {
            for (i in 0..15) {
                item {
                    CategoryItem(
                        id = 1,
                        title = "Kategoriyalar",
                        quantity = 22
                    ) {

                    }
                }
            }
        }
    }
}