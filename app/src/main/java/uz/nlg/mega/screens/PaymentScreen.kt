package uz.nlg.mega.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.Customer
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E6
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.PaymentType
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.PaymentItem
import uz.nlg.mega.views.PriceTextField
import uz.nlg.mega.views.SecondaryButton
import uz.nlg.mega.views.SecondaryButtonWithIcon
import uz.nlg.mega.views.SimpleTextField

@Destination
@Composable
fun PaymentScreen(
    navigator: DestinationsNavigator? = null,
    cheque: Cheque
) {

    val paymentMethods = remember {
        mutableStateListOf<PaymentType>(PaymentType.Cash)
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
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = if (cheque.customer == null) Arrangement.End else Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (cheque.customer != null) SimpleTextField(
                            modifier = Modifier
                                .weight(1f),
                            hint = "",
                            text = cheque.customer.name,
                            backgroundColor = Color.White,
                            strokeColor = Color_E8,
                            textColor = ItemTextColor,
                            readOnly = true
                        ) {}

                        SecondaryButtonWithIcon(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            text = stringResource(id = R.string.str_customers_list),
                            icon = painterResource(id = R.drawable.customers),
                            isCustomerHave = cheque.customer != null
                        ) {

                        }

                    }

                    Column (
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
                                title = stringResource(id = PaymentType.Terminal.title),
                                isSelected = paymentMethods.contains(PaymentType.Terminal)
                            ) {
                                val type = PaymentType.Terminal
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

                            Spacer(modifier = Modifier.width(10.dp))

                            PaymentItem(
                                modifier = Modifier
                                    .weight(.1f),
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

                        }
                    }

                    Column (
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

                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.Terminal)) {
                            Column (
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                            ) {
                                Text(
                                    text = stringResource(id = PaymentType.Terminal.title),
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

                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.OnlinePayment)) {
                            Column (
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

                                }
                            }
                        }

                        AnimatedVisibility(paymentMethods.contains(PaymentType.Credit)) {
                            Column (
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
                        text = cheque.totalPrice.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(PADDING_VALUE))

                MainButton(
                    modifier = Modifier
                        .padding(horizontal = PADDING_VALUE)
                        .padding(bottom = PADDING_VALUE),
                    text = stringResource(id = R.string.str_pay),
                    textColor = Color.White,
                    textSize = 16.sp,
                    backgroundColor = MainColor,
                    strokeColor = MainColor
                ) {

                }
            }
        }

    }


}

@Preview
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        cheque = Cheque(
            type = ChequeType.Paid,
            serialNumber = 12345,
            clientName = "Ogabek Matyakubov",
            date = "15.10.2023",
            time = "15:23",
            products = OrderProducts,
            totalPrice = 1_500_000,
            customer = Customer(
                id = 1,
                name = "Ogabek Matyakubov",
                phoneNumber = "93 203 73 13",
                priceDIff = -1234456
            )
        )
    )
}
