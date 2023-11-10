package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.Client
import uz.nlg.mega.model.Seller
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_AF
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.dateToString
import uz.nlg.mega.utils.findChequeType
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.typeColor

@Composable
fun ChequeItem(
    cheque: Cheque,
    onDeleteClick: ((Cheque) -> Unit)? = null,
    onItemClick: ((Cheque) -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(13.dp))
            .border(
                width = 1.dp,
                color = Color_E8,
                shape = RoundedCornerShape(13.dp)
            )
            .background(Color.White)
            .clickable {
                onItemClick?.invoke(cheque)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 10.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${stringResource(id = R.string.str_seria)} ${cheque.serialNumber}",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color.Black,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "${cheque.client?.firstName} ${cheque.client?.lastName}",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = MainColor
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = dateToString(cheque.createdAt),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color_AF,
                        maxLines = 1
                    )

                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = cheque.chequeSum.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color.Black,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "${cheque.itemsCount} ${stringResource(id = R.string.str_quantity)}",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = Color_66,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = stringResource(id = findChequeType(cheque.status)),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = typeColor(cheque.status),
                        maxLines = 1
                    )

                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_delete_blue),
                contentDescription = null,
                tint = MainColor,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp, 20.dp)
                    .clip(CircleShape)
                    .clickable {
                        onDeleteClick!!.invoke(cheque)
                    }
            )

        }
    }

}

@Preview
@Composable
fun ChequeItemPreview() {
    ChequeItem(
        cheque = Cheque(
            chequeSum = 2_400_000,
            client = Client(
                firstName = "Bogibek",
                id = 12,
                lastName = "Matyaqubov",
                phoneNumber = "122112221",
                extraInfo = "",
                balance = 0L
            ),
            createdAt = "2023-11-01T12:38:41.021783Z",
            id = 12,
            itemsCount = 123,
            seller = Seller(
                firstName = "Qummi",
                id = 12,
                lastName = "Azamat"
            ),
            serialNumber = 2131232,
            status = "done"
        ),
    )
}