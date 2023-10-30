package uz.nlg.mega.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.screens.destinations.ChequeDetailsScreenDestination
import uz.nlg.mega.screens.destinations.ChequeItemListScreenDestination
import uz.nlg.mega.ui.theme.Color_11
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_BD
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.utils.Cheques
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.utils.typeColor
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.ChequeProductsItem
import uz.nlg.mega.views.SecondaryButton

@Destination
@Composable
fun ChequeDetailsScreen(
    navigator: DestinationsNavigator? = null,
    cheque: Cheque
) {
    val context = LocalContext.current
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
                        text = cheque.totalPrice.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color_11,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${cheque.date} ${cheque.time}",
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
                            text = "№ ${cheque.serialNumber}",
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
                            text = "${cheque.date} ${cheque.time}",
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
                                text = cheque.customer!!.name,
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color_66
                            )
                            Text(
                                text = cheque.customer.phoneNumber,
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
                            text = cheque.clientName,
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
                            text = stringResource(id = cheque.type.title!!),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = typeColor(cheque.type)
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
                        val size = if (cheque.products.size >= 3) 2 else cheque.products.size
                        for (i in 0..size) {
                            ChequeProductsItem(product = cheque.products[i])
                        }
                    }

                    Spacer(modifier = Modifier.height(PADDING_VALUE))

                    SecondaryButton(
                        modifier = Modifier,
                        text = stringResource(R.string.str_see_more),
                        textSize = 14.sp
                    ) {
                        navigator!!.screenNavigate(ChequeItemListScreenDestination(cheque.products))
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
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = stringResource(R.string.str_all),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color_BD,
                        )

                        Text(
                            text = cheque.totalPrice.moneyType(),
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color_66,
                        )
                    }

                    Column (
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color_BD
                                    )
                                ) {
                                    append(stringResource(id = R.string.str_cash))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = Color_66
                                    )
                                ) {
                                    append(" " + cheque.totalPrice.moneyType())
                                }
                            },
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color_BD,
                        )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color_BD
                                    )
                                ) {
                                    append(stringResource(id = R.string.str_card))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = Color_66
                                    )
                                ) {
                                    append(" " + cheque.totalPrice.moneyType())
                                }
                            },
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color_66,
                        )
                    }

                }
            }
        }
    }

}

@Preview
@Composable
fun ChequeDetailsScreenPreview() {
    ChequeDetailsScreen(
        cheque = Cheques[0]
    )
}