package uz.nlg.mega.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import uz.nlg.mega.R
import uz.nlg.mega.model.Cart
import uz.nlg.mega.model.ChequePayment
import uz.nlg.mega.mvvm.PaymentViewModel
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E6
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.PaymentType
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.PaymentItem
import uz.nlg.mega.views.PriceTextField

@Destination
@Composable
fun PaymentScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: PaymentViewModel = hiltViewModel(),
    cart: Cart,
    totalPrice: Long
) {

    val paymentMethods = remember {
        mutableStateListOf<PaymentType>(PaymentType.Cash)
    }

    var cash by remember {
        mutableStateOf(0L)
    }

    var online by remember {
        mutableStateOf(0L)
    }

    var humo by remember {
        mutableStateOf(0L)
    }

    var uzcard by remember {
        mutableStateOf(0L)
    }

    var credit by remember {
        mutableStateOf(0L)
    }

    var currentPrice by remember {
        mutableStateOf(0L)
    }

    LaunchedEffect(cash, online, humo, uzcard, credit) {
        currentPrice = cash + online + humo + uzcard + credit
    }

    if (viewModel.isGoLogin.value) {
        navigateToLoginScreen(LocalContext.current)
    }

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()
        viewModel.errorMessage.value = null
    }

    if (viewModel.isDone.value) {
        navigator!!.navigateUp()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 140.dp)
        ) {
            BackTopSection(title = stringResource(id = R.string.str_pay)) {
                navigator!!.navigateUp()
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = PADDING_VALUE)
                    .padding(top = PADDING_VALUE)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 10.dp),
                            text = stringResource(id = R.string.str_payment_type),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color_66
                        )

                        Row {

                            PaymentItem(
                                modifier = Modifier
                                    .weight(.1f),
                                title = stringResource(id = PaymentType.Cash.title),
                                isSelected = paymentMethods.contains(PaymentType.Cash)
                            ) {
                                val type = PaymentType.Cash
                                if (paymentMethods.contains(type)) {
                                    if (paymentMethods.size <= 1)
                                        return@PaymentItem false
                                    paymentMethods.remove(type)
                                } else
                                    paymentMethods.add(type)

                                return@PaymentItem true
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            PaymentItem(
                                modifier = Modifier
                                    .weight(.1f),
                                title = stringResource(id = PaymentType.OnlinePayment.title),
                                isSelected = paymentMethods.contains(PaymentType.OnlinePayment)
                            ) {
                                val type = PaymentType.OnlinePayment
                                if (paymentMethods.contains(type)) {
                                    if (paymentMethods.size <= 1)
                                        return@PaymentItem false
                                    paymentMethods.remove(type)
                                } else
                                    paymentMethods.add(type)

                                return@PaymentItem true
                            }

                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {

                            PaymentItem(
                                modifier = Modifier
                                    .weight(.1f),
                                title = stringResource(id = PaymentType.Humo.title),
                                isSelected = paymentMethods.contains(PaymentType.Humo)
                            ) {
                                val type = PaymentType.Humo
                                if (paymentMethods.contains(type)) {
                                    if (paymentMethods.size <= 1)
                                        return@PaymentItem false
                                    paymentMethods.remove(type)
                                } else
                                    paymentMethods.add(type)

                                return@PaymentItem true
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            PaymentItem(
                                modifier = Modifier
                                    .weight(.1f),
                                title = stringResource(id = PaymentType.UzCard.title),
                                isSelected = paymentMethods.contains(PaymentType.UzCard)
                            ) {
                                val type = PaymentType.UzCard
                                if (paymentMethods.contains(type)) {
                                    if (paymentMethods.size <= 1)
                                        return@PaymentItem false
                                    paymentMethods.remove(type)
                                } else
                                    paymentMethods.add(type)

                                return@PaymentItem true
                            }

                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {

                            PaymentItem(
                                modifier = Modifier
                                    .weight(1f),
                                title = stringResource(id = PaymentType.Credit.title),
                                isSelected = paymentMethods.contains(PaymentType.Credit)
                            ) {
                                val type = PaymentType.Credit
                                if (paymentMethods.contains(type)) {
                                    if (paymentMethods.size <= 1)
                                        return@PaymentItem false
                                    paymentMethods.remove(type)
                                } else
                                    paymentMethods.add(type)

                                return@PaymentItem true
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Spacer(modifier = Modifier.weight(1f))

                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(top = PADDING_VALUE),
                        horizontalAlignment = Alignment.Start
                    ) {
                        AnimatedVisibility(paymentMethods.contains(PaymentType.Cash)) {
                            Column(
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.Cash.title),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )

                                Spacer(Modifier.height(10.dp))

                                PriceTextField(
                                    modifier = Modifier,
                                    hint = "0",
                                    text = "",
                                    backgroundColor = Color.White,
                                    strokeColor = Color_E8,
                                    textColor = ItemTextColor,
                                    textLimit = 25
                                ) {
                                    cash = it
                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.OnlinePayment)) {
                            Column(
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.OnlinePayment.title),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )

                                Spacer(Modifier.height(10.dp))

                                PriceTextField(
                                    modifier = Modifier,
                                    hint = "0",
                                    text = "",
                                    backgroundColor = Color.White,
                                    strokeColor = Color_E8,
                                    textColor = ItemTextColor,
                                    textLimit = 25
                                ) {
                                    online = it
                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.Humo)) {
                            Column(
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.Humo.title),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )

                                Spacer(Modifier.height(10.dp))

                                PriceTextField(
                                    modifier = Modifier,
                                    hint = "0",
                                    text = "",
                                    backgroundColor = Color.White,
                                    strokeColor = Color_E8,
                                    textColor = ItemTextColor,
                                    textLimit = 25
                                ) {
                                    humo = it
                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.UzCard)) {
                            Column(
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.UzCard.title),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )

                                Spacer(Modifier.height(10.dp))

                                PriceTextField(
                                    modifier = Modifier,
                                    hint = "0",
                                    text = "",
                                    backgroundColor = Color.White,
                                    strokeColor = Color_E8,
                                    textColor = ItemTextColor,
                                    textLimit = 25
                                ) {
                                    uzcard = it
                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.Credit)) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.Credit.title),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Color_66
                                )

                                Spacer(Modifier.height(10.dp))

                                PriceTextField(
                                    modifier = Modifier,
                                    hint = "0",
                                    text = "",
                                    backgroundColor = Color.White,
                                    strokeColor = Color_E8,
                                    textColor = ItemTextColor,
                                    textLimit = 25
                                ) {
                                    credit = it
                                }
                            }
                        }
                    }
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
                    .height(140.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(Color_E6)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = PADDING_VALUE)
                        .padding(horizontal = PADDING_VALUE),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.str_total),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Text(
                        text = totalPrice.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(PADDING_VALUE))

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PADDING_VALUE)
                        .padding(bottom = PADDING_VALUE),
                    text = stringResource(id = R.string.str_pay) + "   ${currentPrice.moneyType()}",
                    textColor = Color.White,
                    textSize = 16.sp,
                    backgroundColor = MainColor,
                    strokeColor = MainColor
                ) {
                    val payments = arrayListOf(
                        ChequePayment(
                            "",
                            0,
                            "cash",
                            cash
                        ),
//                        ChequePayment(
//                            "",
//                            0,
//                            "online",
//                            online
//                        ),
                        ChequePayment(
                            "",
                            0,
                            "humo",
                            humo
                        ),
                        ChequePayment(
                            "",
                            0,
                            "uzcard",
                            uzcard
                        ),
//                        ChequePayment(
//                            "",
//                            0,
//                            "credit",
//                            credit
//                        )
                    )
                    cart.payments = payments
                    cart.status = "done"
                    viewModel.saveCheque(cart, totalPrice, currentPrice)
                }
            }
        }
    }
}