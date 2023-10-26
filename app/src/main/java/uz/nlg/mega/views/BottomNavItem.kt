package uz.nlg.mega.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.model.BottomNav
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.ScreenID

@Composable
fun BottomNavItem(
    item: BottomNav,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(72.dp, 72.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick.invoke()
            },
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp, 34.dp),
            painter = painterResource(id = item.icon),
            contentDescription = null,
            tint = if (isSelected) MainColor else Color_66
        )

        Text(
            text = stringResource(id = item.title),
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = if (isSelected) MainColor else Color_66
        )
    }
}

@Preview
@Composable
fun BottomNavItemPreview() {
    BottomNavItem(
        item = BottomNav(
            id = ScreenID.ChequesScreen,
            route = "cheque",
            icon = R.drawable.orders,
            title = R.string.str_orders
        ),
        isSelected = true
    ) {}
}