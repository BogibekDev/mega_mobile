package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.CheckColor
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.Filter
import uz.nlg.mega.utils.MainFont

@Composable
fun MinusPlusFilterView(
    filter: Filter,
    onSelectedFilter: (Filter) -> Unit
) {

    var filterState by remember {
        mutableStateOf(filter)
    }

    Box(
        modifier = Modifier
            .width(130.dp)
            .border(
                width = 1.dp,
                color = Color_E8,
                shape = RoundedCornerShape(9.dp)
            )
            .background(Color.White)
            .padding(vertical = 16.dp)

    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.Qarzdorlar
                        onSelectedFilter.invoke(filterState)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = stringResource(id = Filter.Qarzdorlar.title!!),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )

                if (filterState == Filter.Qarzdorlar) Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(12.dp, 12.dp),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = CheckColor
                )

            }

            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color_E8)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.Haqdorlar
                        onSelectedFilter.invoke(filterState)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = stringResource(Filter.Haqdorlar.title!!),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )

                if (filterState == Filter.Haqdorlar) Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(12.dp, 12.dp),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = CheckColor
                )

            }

            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color_E8)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.None
                        onSelectedFilter.invoke(filterState)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(id = R.string.str_clear_filter),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = RedTextColor
                )

            }

        }

    }

}

@Composable
fun AscDescFilterView(
    filter: Filter,
    onSelectedFilter: (Filter) -> Unit
) {

    var filterState by remember {
        mutableStateOf(filter)
    }

    Box(
        modifier = Modifier
            .width(190.dp)
            .border(
                width = 1.dp,
                color = Color_E8,
                shape = RoundedCornerShape(9.dp)
            )
            .background(Color.White)
            .padding(vertical = 16.dp)

    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.InAscendingOrder
                        onSelectedFilter.invoke(filter)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = stringResource(Filter.InAscendingOrder.title!!),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )

                if (filterState == Filter.InAscendingOrder) Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(12.dp, 12.dp),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = CheckColor
                )

            }

            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color_E8)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.InDescendingOrder
                        onSelectedFilter.invoke(filterState)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = stringResource(Filter.InDescendingOrder.title!!),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Black
                )

                if (filterState == Filter.InDescendingOrder) Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(12.dp, 12.dp),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = CheckColor
                )

            }

            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color_E8)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        filterState = Filter.None
                        onSelectedFilter.invoke(filterState)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(id = R.string.str_clear_filter),
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = RedTextColor
                )

            }

        }

    }

}

@Preview
@Composable
fun FilterViewPreview() {
    Column {
        MinusPlusFilterView(
            filter = Filter.Haqdorlar
        ) {

        }

        Spacer(Modifier.height(100.dp))

        AscDescFilterView(
            filter = Filter.InAscendingOrder
        ) {

        }
    }
}