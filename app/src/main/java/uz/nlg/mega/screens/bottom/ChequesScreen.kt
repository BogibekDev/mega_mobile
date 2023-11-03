package uz.nlg.mega.screens.bottom

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.ChequeViewModel
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.views.ChequeItem
import uz.nlg.mega.views.DialogMessage
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.SimpleTopSection

var chequesType: ChequeType = ChequeType.None
var isShowDialog = mutableStateOf(false)

@Composable
fun ChequesScreen(
    navigator: DestinationsNavigator,
    viewModel: ChequeViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }

    val page by remember {
        mutableStateOf(1)
    }

    if (viewModel.isGoLogin.value) navigateToLoginScreen(LocalContext.current)

    if (showDialog.value) {
        DialogMessage(
            value = stringResource(id = R.string.str_delete_title),
            setShowDialog = {
                isShowDialog.value = it
                showDialog.value = it
            },
            icon = painterResource(id = R.drawable.ic_delete_blue),
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

    LaunchedEffect(chequeType) {
        viewModel.getCheques(chequeType.status)
    }

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
        viewModel._error.value = null
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
                    text = stringResource(R.string.str_saved),
                    textColor = if (chequeType == ChequeType.Pending) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (chequeType == ChequeType.Pending) MainColor else Color.White,
                    strokeColor = if (chequeType == ChequeType.Pending) MainColor else Color_E8
                ) {
                    if (chequeType == ChequeType.Pending) {
                        chequeType = ChequeType.None
                        chequesType = ChequeType.None
                    } else {
                        chequeType = ChequeType.Pending
                        chequesType = ChequeType.Pending
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                MainButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.str_paid),
                    textColor = if (chequeType == ChequeType.Done) Color.White else Color_66,
                    textSize = 13.sp,
                    isTextBold = false,
                    backgroundColor = if (chequeType == ChequeType.Done) MainColor else Color.White,
                    strokeColor = if (chequeType == ChequeType.Done) MainColor else Color_E8
                ) {
                    if (chequeType == ChequeType.Done) {
                        chequeType = ChequeType.None
                        chequesType = ChequeType.None
                    } else {
                        chequeType = ChequeType.Done
                        chequesType = ChequeType.Done
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                MainButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.str_returned),
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

            if (viewModel.isLoading.value)
                LoadingView()
            else if (viewModel.data.value != null)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),

                    ) {
                    items(viewModel.data.value!!.results.size) { position ->
                        Box(
                            modifier = Modifier.padding(
                                top = 5.dp,
                                bottom = 5.dp,
                                start = PADDING_VALUE,
                                end = PADDING_VALUE,
                            )
                        ) {
                            ChequeItem(cheque = viewModel.data.value!!.results[position],
                                onDeleteClick = {
                                    isShowDialog.value = true
                                    showDialog.value = true
                                },
                                onItemClick = {

                                }
                            )

                        }

                    }
                }
        }

    }
}