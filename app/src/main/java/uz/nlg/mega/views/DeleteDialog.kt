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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.Color_11
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE

@Composable
fun DeleteDialog(
    value: String = stringResource(R.string.str_delete_title),
    setShowDialog: (Boolean) -> Unit,
    yesClicked: () -> Unit
) {

    Dialog(
        onDismissRequest = { setShowDialog(false) }
    ) {
        Box(
            modifier = Modifier
                .padding(PADDING_VALUE)
                .clip(RoundedCornerShape(9.dp))
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.ic_delete_blue),
                    tint = RedTextColor,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(PADDING_VALUE))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(.75f),
                    text = value,
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color_11,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(PADDING_VALUE))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MainButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        text = stringResource(R.string.str_no),
                        textColor = Color_66,
                        textSize = 15.sp,
                        backgroundColor = Color_E8,
                        isTextBold = false,
                        strokeColor = Color_E8,
                    ) {
                        setShowDialog(false)
                    }
                    Spacer(modifier = Modifier.width(PADDING_VALUE))
                    MainButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        text = stringResource(R.string.str_yes),
                        textColor = Color.White,
                        textSize = 15.sp,
                        isTextBold = false,
                        backgroundColor = RedTextColor,
                        strokeColor = RedTextColor,
                    ) {
                        yesClicked.invoke()
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DeleteDialogPreview() {
    DeleteDialog(
        value = stringResource(id = R.string.str_delete_title),
        setShowDialog = {  },
        yesClicked = {}
    )
}