package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.DarkBlueMainColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.TextFieldFillColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE

@Composable
fun PaymentItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onSelectedChange: (isSelected: Boolean) -> Boolean
) {

    var selected by remember {
        mutableStateOf(isSelected)
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (selected) MainColor else TextFieldFillColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(TextFieldFillColor)
            .clickable {
                if (onSelectedChange.invoke(selected)) selected = !selected
            }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(vertical = PADDING_VALUE)
                    .padding(start = 20.dp)
                    .padding(end = 10.dp)
                    .border(
                        width = 1.dp,
                        color = if (selected) DarkBlueMainColor else Color_E8,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clip(RoundedCornerShape(4.dp))
                    .size(20.dp, 20.dp),
                checked = selected,
                onCheckedChange = {
                    if (onSelectedChange.invoke(selected)) selected = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MainColor,
                    uncheckedColor = Color_F6,
                    checkmarkColor = Color.White
                )
            )

            Text(
                modifier = Modifier
                    .padding(end = 20.dp),
                text = title,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color_66
            )

        }
    }
}

@Preview
@Composable
fun PaymentItemPreview() {
    PaymentItem(
        title = stringResource(id = R.string.str_online_payment),
        isSelected = true
    ) {

        true
    }
}