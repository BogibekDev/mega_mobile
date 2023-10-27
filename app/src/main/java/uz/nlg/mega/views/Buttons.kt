package uz.nlg.mega.views

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE

@Composable
fun MainButton(
    modifier: Modifier,
    text: String,
    textColor: Color,
    textSize: TextUnit,
    isTextBold: Boolean = true,
    backgroundColor: Color,
    strokeColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = MainFont,
            color = textColor,
            fontWeight = if (isTextBold) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = textSize
        )
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier,
    text: String,
    textSize: TextUnit,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MainColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = MainFont,
            color = MainColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = textSize
        )
    }

}

@Composable
fun SecondaryButtonWithIcon(
    modifier: Modifier,
    text: String,
    icon: Painter,
    isCustomerHave: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MainColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(!isCustomerHave) {
                Text(
                    modifier = Modifier
                        .padding(start = PADDING_VALUE),
                    text = text,
                    fontFamily = MainFont,
                    color = MainColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }

            Icon(
                modifier = Modifier
                    .padding(PADDING_VALUE)
                    .size(18.dp, 18.dp),
                painter = icon,
                contentDescription = null,
                tint = MainColor
            )
        }
    }

}

@Composable
fun NextButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(44.dp, 44.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MainColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.next),
            contentDescription = null,
            tint = MainColor,
            modifier = Modifier
                .size(24.dp, 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    SecondaryButtonWithIcon(
        modifier = Modifier,
        text = stringResource(id = R.string.str_customers_list),
        icon = painterResource(id = R.drawable.customers),
        true
    ) {
        
    }
}