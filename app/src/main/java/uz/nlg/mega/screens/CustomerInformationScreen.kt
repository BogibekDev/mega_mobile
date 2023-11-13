package uz.nlg.mega.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Client
import uz.nlg.mega.mvvm.ClientInfoViewModel
import uz.nlg.mega.screens.destinations.CreditsHistoryScreenDestination
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.ui.theme.TextFieldFillColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.utils.navigateToLoginScreen
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.BackTopSection
import uz.nlg.mega.views.CustomerInfoTextField
import uz.nlg.mega.views.LoadingView
import uz.nlg.mega.views.MainButton
import uz.nlg.mega.views.SimpleTextField

@Destination
@Composable
fun CustomerInformationScreen(
    navigator: DestinationsNavigator? = null,
    customer: Client? = null,
    viewModel: ClientInfoViewModel = hiltViewModel()
) {

    val client = customer ?: Client(null, "", "", null, "", "")

    val context = LocalContext.current

    var name by remember {
        mutableStateOf(customer?.firstName ?: "")
    }

    var surname by remember {
        mutableStateOf(customer?.lastName ?: "")
    }

    var phoneNumber by remember {
        mutableStateOf(customer?.phoneNumber ?: "")
    }

    var description by remember {
        mutableStateOf(customer?.extraInfo ?: "")
    }

    if (viewModel.isGoLogin.value) navigateToLoginScreen(context)

    if (viewModel.errorMessage.value != null) {
        Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        viewModel.errorMessage.value = null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            BackTopSection(
                title = if (customer != null) stringResource(id = R.string.str_private_info)
                else stringResource(R.string.str_add_customer)
            ) {
                navigator!!.navigateUp()
            }


            if (viewModel.data.value != null) {
                Toast.makeText(context, stringResource(R.string.str_info_save), Toast.LENGTH_SHORT)
                    .show()
                navigator!!.navigateUp()
            }

            if (viewModel.isLoading.value) LoadingView()
            else LazyColumn(
                modifier = Modifier
                    .padding(PADDING_VALUE)
            ) {
                item {
                    CustomerInfoTextField(
                        modifier = Modifier,
                        hint = stringResource(R.string.str_name),
                        text = name,
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ) {
                        name = it
                    }

                    Spacer(modifier = Modifier.height(PADDING_VALUE))

                    CustomerInfoTextField(
                        modifier = Modifier,
                        hint = stringResource(R.string.str_surname),
                        text = surname,
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ) {
                        surname = it
                    }

                    Spacer(modifier = Modifier.height(PADDING_VALUE))

                    CustomerInfoTextField(
                        modifier = Modifier,
                        hint = stringResource(R.string.str_phone_number),
                        text = phoneNumber,
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ) {
                        phoneNumber = it
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        modifier = Modifier
                            .padding(horizontal = PADDING_VALUE)
                            .padding(bottom = PADDING_VALUE),
                        text = stringResource(id = R.string.str_more_information),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color_66
                    )

                    SimpleTextField(
                        modifier = Modifier
                            .height(100.dp),
                        hint = "",
                        text = description,
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ) {
                        description = it
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {

                        MainButton(
                            modifier = Modifier,
                            text = stringResource(R.string.str_cancel),
                            textColor = MainColor,
                            textSize = 14.sp,
                            backgroundColor = Color.White,
                            strokeColor = MainColor
                        ) {
                            navigator!!.navigateUp()
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        MainButton(
                            modifier = Modifier,
                            text = stringResource(R.string.str_save),
                            textColor = Color_E8,
                            textSize = 14.sp,
                            backgroundColor = MainColor,
                            strokeColor = MainColor
                        ) {
                            if (name.isNotBlank() && phoneNumber.isNotBlank() && surname.isNotBlank()) {
                                client.firstName = name.trim()
                                client.lastName = surname.trim()
                                client.phoneNumber = phoneNumber.trim()
                                client.extraInfo = description.trim()

                                if (customer == null) viewModel.addClient(client)
                                else viewModel.editClient(client)

                            } else {
                                Toast.makeText(
                                    context,
                                    context.getText(R.string.str_enter_value_warning),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                        }
                    }
                    if (customer != null) Column {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .padding(bottom = 15.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = if ((customer.balance ?: 0L) < 0L) {
                                    stringResource(id = R.string.str_total_credit)
                                } else {
                                    stringResource(id = R.string.str_total_invest)
                                },
                                fontFamily = MainFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color_66
                            )

                            Text(
                                modifier = Modifier,
                                text = (customer.balance ?: 0L).moneyType(),
                                fontFamily = MainFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = if ((customer.balance ?: 0L) < 0L) {
                                    RedTextColor
                                } else {
                                    GreenColor
                                }
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(bottom = 30.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color_E8)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            MainButton(
                                modifier = Modifier,
                                text = stringResource(R.string.str_to_see_credit_history),
                                textColor = MainColor,
                                textSize = 14.sp,
                                backgroundColor = Color.White,
                                strokeColor = MainColor
                            ) {
                                navigator!!.screenNavigate(CreditsHistoryScreenDestination(clientId = client.id!!))
                            }
                        }
                    }

                }
            }

        }
    }
}

@Preview
@Composable
fun CustomerInformationScreenPreview() {
    CustomerInformationScreen()
}