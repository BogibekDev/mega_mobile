package uz.nlg.mega.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.R
import uz.nlg.mega.model.Product
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_BD
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.RadioButtonColor
import uz.nlg.mega.ui.theme.TextFieldFillColor
import uz.nlg.mega.ui.theme.TextFieldHintColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType
import uz.nlg.mega.views.AmountTextField
import uz.nlg.mega.views.DoneTopSection

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun EnterQuantityScreen(
    navigator: DestinationsNavigator? = null,
    product: Product
) {

    var productType by remember {
        mutableStateOf(product.firstQuantityType)
    }

    var quantity by remember {
        mutableStateOf(0L)
    }

    var productPrice by remember {
        mutableStateOf(0L)
    }

    var totalPrice by remember {
        mutableStateOf(0L)
    }

    Box(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
            .background(Color_F6)
    ) {
        Column {
            DoneTopSection(onBackClick = {
                navigator!!.navigateUp()
            }) {
                navigator!!.navigateUp()
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PADDING_VALUE)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.str_name_of_product),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color_BD
                    )

                    Text(
                        text = product.name,
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color_66
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.str_enter_price),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color_BD
                    )

                    Text(
                        text = product.price.moneyType(),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color_66
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.str_left),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color_BD
                    )

                    Text(
                        text = "${product.quantity} ta",
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color_66
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PADDING_VALUE)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = productType == product.firstQuantityType,
                            onClick = {
                                productType = product.firstQuantityType
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MainColor,
                                unselectedColor = RadioButtonColor,
                                disabledSelectedColor = MainColor,
                                disabledUnselectedColor = RadioButtonColor
                            )
                        )

                        Text(
                            text = product.firstQuantityType,
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color_66
                        )

                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = productType == product.secondQuantityType,
                            onClick = {
                                productType = product.secondQuantityType
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MainColor,
                                unselectedColor = RadioButtonColor,
                                disabledSelectedColor = MainColor,
                                disabledUnselectedColor = RadioButtonColor
                            )
                        )

                        Text(
                            text = product.secondQuantityType,
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color_66
                        )

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.str_quantity_item),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color_66
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AmountTextField(
                        modifier = Modifier
                            .height(50.dp),
                        hint = "0",
                        text = "",
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        textSize = 14.sp,
                        textLimit = 4,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ) {
                        quantity = it
                        totalPrice = quantity * productPrice
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.str_price),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color_66
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AmountTextField(
                        modifier = Modifier
                            .height(50.dp),
                        hint = "0",
                        text = "",
                        backgroundColor = TextFieldFillColor,
                        strokeColor = Color_E8,
                        textColor = ItemTextColor,
                        textSize = 14.sp,
                        textLimit = 25,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ) {
                        productPrice = it
                        totalPrice = quantity * productPrice
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.str_t_price),
                        fontFamily = MainFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color_66
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color_E8,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        value = if (totalPrice == 0L) "" else totalPrice.moneyType(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldFillColor,
                            cursorColor = ItemTextColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        readOnly = true,
                        textStyle = TextStyle(
                            color = ItemTextColor,
                            fontSize = 14.sp,
                            fontFamily = MainFont,
                            fontWeight = FontWeight.Normal
                        ),
                        onValueChange = {},
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "0",
                                fontSize = 14.sp,
                                fontFamily = MainFont,
                                fontWeight = FontWeight.Normal,
                                color = TextFieldHintColor
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

            }

        }
    }
}

@Preview
@Composable
fun EnterQuantityScreenPreview() {
    EnterQuantityScreen(
        product = Product(
            id = 1,
            name = "Produckt name",
            quantity = 22,
            firstQuantityType = "Dona",
            secondQuantityType = "Blok",
            coefficient = "",
            price = 1_300_000
        )
    )
}