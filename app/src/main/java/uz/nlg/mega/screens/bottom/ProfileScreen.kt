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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.mvvm.ProfileViewModel
import uz.nlg.mega.screens.destinations.CreditInformationScreenDestination
import uz.nlg.mega.screens.destinations.ReturnCreditsScreenDestination
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.OrangeColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.CreditType
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.DialogMessage
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.NextButton
import uz.nlg.mega.views.ProfileTopSection
import uz.nlg.mega.views.SecondaryButton

@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.getProfileInformation()
    }

    if (viewModel.isGoLogin.value) navigateToLoginScreen(LocalContext.current)

    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        DialogMessage(
            value = stringResource(id = R.string.str_log_out_title),
            setShowDialog = {
                isShowDialog.value = it
                showDialog.value = it
            },
            icon = painterResource(id = R.drawable.ic_log_out),
            yesClicked = {
                showDialog.value = false
                isShowDialog.value = false

                viewModel.logOut()

            }
        )
    }

    if (!viewModel.errorMessage.value.isNullOrEmpty())
        Toast.makeText(LocalContext.current, viewModel.errorMessage.value, Toast.LENGTH_SHORT)
            .show()

    if (viewModel.isLoggedOut.value) {
        navigateToLoginScreen(LocalContext.current)
    }

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
                isShowDialog.value = true
                showDialog.value = true
            }

            if (viewModel.isLoading.value)
                LoadingView()
            else
                if (viewModel.myProfile.value != null) LazyColumn(
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
                                text = viewModel.myProfile.value!!.firstName + " " + viewModel.myProfile.value!!.lastName,
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
                                val dailyTotalPriceStr =
                                    stringResource(id = R.string.str_total_trade_price)
                                val dailyTotalLosePriceStr =
                                    stringResource(id = R.string.str_total_lose_price)
                                val dailyTotalReturnedPriceStr =
                                    stringResource(id = R.string.str_total_returned_price)

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
                                            append(viewModel.myProfile.value!!.dayTrade.sold.moneyType())
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
                                            append(viewModel.myProfile.value!!.dayTrade.harm.moneyType())
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
                                            append(viewModel.myProfile.value!!.dayTrade.returned.moneyType())
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
                                val monthlyTotalPriceStr =
                                    stringResource(id = R.string.str_total_trade_price)
                                val monthlyTotalLosePriceStr =
                                    stringResource(id = R.string.str_total_lose_price)
                                val monthlyTotalReturnedPriceStr =
                                    stringResource(id = R.string.str_total_returned_price)

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
                                            append(viewModel.myProfile.value!!.monthTrade.sold.moneyType())
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
                                            append(viewModel.myProfile.value!!.monthTrade.harm.moneyType())
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
                                            append(viewModel.myProfile.value!!.monthTrade.returned.moneyType())
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

                        Column(
                            modifier = Modifier
                                .padding(PADDING_VALUE)
                                .clip(RoundedCornerShape(9.dp))
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            Row(
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
                                        text = viewModel.myProfile.value!!.dayDebt.moneyType(),
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
                                    navigator!!.screenNavigate(
                                        CreditInformationScreenDestination(
                                            type = CreditType.Daily
                                        )
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

                            Row(
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
                                        text = viewModel.myProfile.value!!.monthDebt.moneyType(),
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
                                    navigator!!.screenNavigate(
                                        CreditInformationScreenDestination(
                                            type = CreditType.Monthly
                                        )
                                    )
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