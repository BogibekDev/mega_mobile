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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.Customer
import uz.nlg.mega.screens.ReturnCreditsScreen
import uz.nlg.mega.screens.destinations.ReturnCreditsScreenDestination
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.OrangeColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.ChequeType
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.NextButton
import uz.nlg.mega.views.ProfileTopSection
import uz.nlg.mega.views.SecondaryButton

@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator? = null
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color_F6)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileTopSection {

            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(PADDING_VALUE)
                            .clip(RoundedCornerShape(9.dp))
                            .fillMaxWidth()
                            .background(Color.White),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(PADDING_VALUE),
                            text = "Ogabek Matyakubov",
                            fontFamily = MainFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = PADDING_VALUE)
                                .background(Color_E8)
                        )

                        Column {
                            val dailyTotalPriceStr = stringResource(id = R.string.str_total_trade_price)
                            val dailyTotalLosePriceStr = stringResource(id = R.string.str_total_lose_price)
                            val dailyTotalReturnedPriceStr = stringResource(id = R.string.str_total_returned_price)

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(top = PADDING_VALUE)
                                    .padding(bottom = 5.dp),
                                text = stringResource(id = R.string.str_daily_trade),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(vertical = 5.dp),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$dailyTotalPriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = GreenColor
                                        )
                                    ) {
                                        append((1000000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(vertical = 5.dp),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$dailyTotalLosePriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = RedTextColor
                                        )
                                    ) {
                                        append((200000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(top = 5.dp)
                                    .padding(bottom = PADDING_VALUE),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$dailyTotalReturnedPriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = OrangeColor
                                        )
                                    ) {
                                        append((2000000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = PADDING_VALUE)
                                .background(Color_E8)
                        )

                        Column {
                            val monthlyTotalPriceStr = stringResource(id = R.string.str_total_trade_price)
                            val monthlyTotalLosePriceStr = stringResource(id = R.string.str_total_lose_price)
                            val monthlyTotalReturnedPriceStr = stringResource(id = R.string.str_total_returned_price)

                            Text(
                                modifier = Modifier
                                    .padding(top = PADDING_VALUE)
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(bottom = 5.dp),
                                text = stringResource(id = R.string.str_monthly_trade),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(vertical = 5.dp),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$monthlyTotalPriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = GreenColor
                                        )
                                    ) {
                                        append((18000000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(vertical = 5.dp),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$monthlyTotalLosePriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = RedTextColor
                                        )
                                    ) {
                                        append((2000000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = PADDING_VALUE)
                                    .padding(top = 5.dp)
                                    .padding(bottom = PADDING_VALUE),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Black
                                        )
                                    ) {
                                        append("$monthlyTotalReturnedPriceStr  ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = OrangeColor
                                        )
                                    ) {
                                        append((5500000L).moneyType())
                                    }
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        }

                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = PADDING_VALUE)
                            .background(Color_E8)
                    )

                    Column (
                        modifier = Modifier
                            .padding(PADDING_VALUE)
                            .clip(RoundedCornerShape(9.dp))
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier
                                        .padding(top = PADDING_VALUE)
                                        .padding(start = PADDING_VALUE),
                                    text = stringResource(id = R.string.str_given_credits_in_a_day),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    modifier = Modifier
                                        .padding(start = PADDING_VALUE)
                                        .padding(bottom = PADDING_VALUE),
                                    text = (200000L).moneyType(),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp,
                                    color = RedTextColor
                                )
                            }

                            NextButton(
                                modifier = Modifier
                                    .padding(end = PADDING_VALUE)
                            ) {

                            }

                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = PADDING_VALUE)
                                .background(Color_E8)
                        )

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier
                                        .padding(top = PADDING_VALUE)
                                        .padding(start = PADDING_VALUE),
                                    text = stringResource(id = R.string.str_given_credits_in_a_month),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    modifier = Modifier
                                        .padding(start = PADDING_VALUE)
                                        .padding(bottom = PADDING_VALUE),
                                    text = (230000000L).moneyType(),
                                    fontFamily = MainFont,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp,
                                    color = RedTextColor
                                )
                            }

                            NextButton(
                                modifier = Modifier
                                    .padding(end = PADDING_VALUE)
                            ) {

                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    SecondaryButton(
                        modifier = Modifier,
                        text = stringResource(id = R.string.str_return_credits),
                        textSize = 15.sp
                    ) {
                        navigator!!.screenNavigate(
                            ReturnCreditsScreenDestination()
                        )
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen()
}