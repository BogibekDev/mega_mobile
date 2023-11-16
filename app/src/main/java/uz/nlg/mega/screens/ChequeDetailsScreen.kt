package uz.nlg.mega.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.ChequeDetailViewModel
import uz.nlg.mega.screens.destinations.ChequeItemListScreenDestination
import uz.nlg.mega.ui.theme.Color_11
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.asPaymentType
import uz.nlg.mega.utils.dateToString
import uz.nlg.mega.utils.findChequeType
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.utils.typeColor
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.ChequeProductsItem
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.SecondaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ChequeDetailsScreen(
    navigator: DestinationsNavigator? = null,
    id: Int,
    viewModel: ChequeDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var isMoreDialogShow by remember {
        mutableStateOf(false)
    }

    if (viewModel.isGoLogin.value) navigateToLoginScreen(context)

    LaunchedEffect(true) {
        viewModel.getChequeDetail(id)
    }

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        viewModel.errorMessage.value = null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            ) {
            BackTopSection(
                title = stringResource(R.string.str_about_cheque)
            ) {
                navigator!!.navigateUp()
            }

            if (viewModel.isLoading.value) LoadingView()
            else if (viewModel.data.value != null)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = PADDING_VALUE)
                        .padding(bottom = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = viewModel.data.value!!.chequeSum.moneyType(),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color_11,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = dateToString(viewModel.data.value!!.createdAt),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            color = Color_66
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 14.dp,
                                    horizontal = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.str_cheque_seria_number),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                color = Color_66
                            )
                            Text(
                                text = "№ ${viewModel.data.value!!.serialNumber}",
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color_66
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 14.dp,
                                    horizontal = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.str_date_time),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                color = Color_66
                            )
                            Text(
                                text = dateToString(viewModel.data.value!!.createdAt),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color_66
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 6.dp,
                                    horizontal = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.str_customer),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                color = Color_66
                            )
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${viewModel.data.value!!.client.firstName} ${viewModel.data.value!!.client.lastName}",
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )
                                Text(
                                    text = viewModel.data.value!!.client.phoneNumber ?: "",
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 14.dp,
                                    horizontal = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.str_seller),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                color = Color_66
                            )
                            Text(
                                text = "${viewModel.data.value!!.seller.firstName} ${viewModel.data.value!!.seller.lastName}",
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color_66
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 14.dp,
                                    horizontal = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.str_cheque_status),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                color = Color_66
                            )
                            Text(
                                text = stringResource(id = findChequeType(viewModel.data.value!!.status)),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = typeColor(viewModel.data.value!!.status)
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(R.string.str_products_list),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color_11,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color_E8)
                        )

                        Column(modifier = Modifier.fillMaxWidth()) {
                            val size =
                                if (viewModel.data.value!!.chequeItems.size >= 3) 3 else viewModel.data.value!!.chequeItems.size
                            for (i in 0 until size) {
                                ChequeProductsItem(item = viewModel.data.value!!.chequeItems[i])
                            }
                        }

                        Spacer(modifier = Modifier.height(PADDING_VALUE))

                        if (viewModel.data.value!!.chequeItems.size >= 2)
                            SecondaryButton(
                                modifier = Modifier,
                                text = stringResource(R.string.str_see_more),
                                textSize = 14.sp
                            ) {
                                navigator!!.screenNavigate(ChequeItemListScreenDestination(viewModel.data.value!!.chequeItems))
                            }

                        Spacer(modifier = Modifier.height(PADDING_VALUE))
                    }

                }

        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = PADDING_VALUE),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color_E8)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${stringResource(id = R.string.str_total)}: ${viewModel.data.value?.chequeSum?.moneyType()}",
                        fontFamily = MainFont,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color_66
                    )

                    Text(
                        modifier = Modifier
                            .clickable {
                                isMoreDialogShow = true
                            },
                        text = stringResource(id = R.string.str_more_info),
                        fontFamily = MainFont,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = MainColor
                    )

                }
            }
        }

        val state = rememberModalBottomSheetState()
        val coroutine = rememberCoroutineScope()

        if (isMoreDialogShow) ModalBottomSheet(
            sheetState = state,
            onDismissRequest = {
                isMoreDialogShow = false
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = PADDING_VALUE + PADDING_VALUE)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.str_total) + ":",
                    fontFamily = MainFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color_66
                )

                Text(
                    text = viewModel.data.value!!.chequeSum.moneyType(),
                    fontFamily = MainFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color_66
                )
            }

            Spacer(
                modifier = Modifier
                    .padding(PADDING_VALUE)
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color_E8)
            )

            LazyColumn {
                items(viewModel.data.value!!.chequePayments.size) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = PADDING_VALUE + PADDING_VALUE)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(viewModel.data.value!!.chequePayments[it].paymentType.asPaymentType()),
                            fontFamily = MainFont,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color_66
                        )

                        Text(
                            text = viewModel.data.value!!.chequePayments[it].price.moneyType(),
                            fontFamily = MainFont,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color_66
                        )
                    }

                    Spacer(modifier = Modifier.height(PADDING_VALUE))
                }
            }

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PADDING_VALUE),
                text = stringResource(id = R.string.str_close),
                textColor = Color.White,
                textSize = 16.sp,
                backgroundColor = MainColor,
                strokeColor = MainColor
            ) {
                coroutine.launch {
                    state.hide()
                    isMoreDialogShow = false
                }
            }

            Spacer(modifier = Modifier.height(PADDING_VALUE))

        }
    }
}