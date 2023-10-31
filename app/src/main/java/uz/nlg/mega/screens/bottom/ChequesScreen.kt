package uz.nlg.mega.screens.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.screens.destinations.ChequeDetailsScreenDestination
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.Cheques
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.ChequeItem
import uz.nlg.mega.views.DeleteDialog
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.SimpleTopSection

var chequesType: ChequeType = ChequeType.None
var isShowDialog = mutableStateOf(false)

@Composable
fun ChequesScreen(
    navigator: DestinationsNavigator
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        DeleteDialog(
            value = stringResource(id = R.string.str_delete_title),
            setShowDialog = {
                isShowDialog.value = it
                showDialog.value = it
            },
            yesClicked = {
                //delete request
                showDialog.value = false
                isShowDialog.value = false
            }
        )
    }

    var chequeType by remember {
        mutableStateOf(chequesType)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color_F6),
    ) {
        Column {
            SimpleTopSection(
                title = stringResource(id = R.string.str_cheques),
                name = "Azamatjon",
            )

            Spacer(modifier = Modifier.height(PADDING_VALUE))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = PADDING_VALUE),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MainButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(ChequeType.Saved.title!!),
                    textColor = if (chequeType == ChequeType.Saved) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (chequeType == ChequeType.Saved) MainColor else Color.White,
                    strokeColor = if (chequeType == ChequeType.Saved) MainColor else Color_E8
                ) {
                    if (chequeType == ChequeType.Saved) {
                        chequeType = ChequeType.None
                        chequesType = ChequeType.None
                    } else {
                        chequeType = ChequeType.Saved
                        chequesType = ChequeType.Saved
                    }


                }

                Spacer(modifier = Modifier.width(8.dp))

                MainButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(ChequeType.Paid.title!!),
                    textColor = if (chequeType == ChequeType.Paid) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (chequeType == ChequeType.Paid) MainColor else Color.White,
                    strokeColor = if (chequeType == ChequeType.Paid) MainColor else Color_E8
                ) {
                    if (chequeType == ChequeType.Paid) {
                        chequeType = ChequeType.None
                        chequesType = ChequeType.None
                    } else {
                        chequeType = ChequeType.Paid
                        chequesType = ChequeType.Paid
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                MainButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(ChequeType.Returned.title!!),
                    textColor = if (chequeType == ChequeType.Returned) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (chequeType == ChequeType.Returned) MainColor else Color.White,
                    strokeColor = if (chequeType == ChequeType.Returned) MainColor else Color_E8
                ) {
                    if (chequeType == ChequeType.Returned) {
                        chequeType = ChequeType.None
                        chequesType = ChequeType.None
                    } else {
                        chequeType = ChequeType.Returned
                        chequesType = ChequeType.Returned
                    }
                }
            }

            Spacer(modifier = Modifier.height(PADDING_VALUE))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),

                ) {
                items(Cheques.size) { position ->
                    Box(
                        modifier = Modifier.padding(
                            top = 5.dp,
                            bottom = 5.dp,
                            start = PADDING_VALUE,
                            end = PADDING_VALUE,
                        )
                    ) {
                        ChequeItem(cheque = Cheques[position],
                            onDeleteClick = {
                                isShowDialog.value = true
                                showDialog.value = true
                            },
                            onItemClick = {
                                navigator.screenNavigate(
                                    ChequeDetailsScreenDestination(
                                        cheque = it
                                    )
                                )
                            }
                        )

                    }

                }
            }
        }

    }
}