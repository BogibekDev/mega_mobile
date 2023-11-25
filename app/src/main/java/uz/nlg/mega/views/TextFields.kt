package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.Color_66
import uz.nlg.mega.ui.theme.Color_BD
import uz.nlg.mega.ui.theme.Color_E8
import uz.nlg.mega.ui.theme.Color_F6
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.TextColorTextField
import uz.nlg.mega.ui.theme.TextFieldFillColor
import uz.nlg.mega.ui.theme.TextFieldHintColor
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.PADDING_VALUE
import uz.nlg.mega.utils.moneyType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    backgroundColor: Color,
    strokeColor: Color,
    textColor: Color,
    textSize: TextUnit = 16.sp,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isEditable: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onChangeListener: (text: String) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            ),
        enabled = isEditable,
        value = textState,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            cursorColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal
        ),
        onValueChange = {
            textState = it
            onChangeListener.invoke(textState)
        },
        readOnly = readOnly,
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
        placeholder = {
            Text(
                text = hint,
                fontSize = textSize,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                color = TextFieldHintColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    backgroundColor: Color,
    strokeColor: Color,
    textColor: Color,
    textSize: TextUnit = 16.sp,
    textLimit: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isReadOnly: Boolean = false,
    onChangeListener: (amount: Long) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            ),
        value = TextFieldValue(
            text = textState,
            selection = TextRange(textState.length)
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            cursorColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),

        readOnly = isReadOnly,
        textStyle = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal
        ),
        onValueChange = {
            if (textLimit >= it.text.length) {
                if (it.text.replace(" ", "").isDigitsOnly()) {
                    textState = it.text.replace(" ", "").moneyType()
                    onChangeListener.invoke(
                        if (textState.isNotEmpty())
                            textState.replace(" ", "").toLong()
                        else 0
                    )
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                fontSize = textSize,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                color = TextFieldHintColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    backgroundColor: Color,
    strokeColor: Color,
    textColor: Color,
    onChangeListener: (text: String) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            ),
        value = textState,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            cursorColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = textColor,
            fontSize = 16.sp,
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal
        ),
        onValueChange = {
            textState = it
            onChangeListener.invoke(textState)
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                color = TextFieldHintColor
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                showPassword = !showPassword
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.show_password),
                    contentDescription = null,
                    tint = if (showPassword) TextColorTextField else MainColor
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    onBackClick: () -> Unit,
    onClearClick: (() -> Unit)? = null,
    onChangeListener: (text: String) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(13.dp)
            ),
        value = textState,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color_F6,
            cursorColor = MainColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color_66,
            fontSize = 16.sp,
            fontFamily = MainFont,
            fontWeight = FontWeight.Normal
        ),
        onValueChange = {
            textState = it
            onChangeListener.invoke(textState)
        },
        shape = RoundedCornerShape(13.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                color = TextFieldHintColor
            )
        },
        leadingIcon = {
            IconButton(onClick = {
                onBackClick.invoke()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color_66
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                textState = ""
                onClearClick?.invoke()
            }) {
                if (textState.isNotEmpty()) Icon(
                    painter = painterResource(id = R.drawable.ic_x),
                    contentDescription = null,
                    tint = TextFieldHintColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    backgroundColor: Color,
    strokeColor: Color,
    textColor: Color,
    textLimit: Int = 9,
    onChangeListener: (text: Long) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            )
            .height(height = 55.dp)
            .background(Color_F6),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = modifier
                .weight(1f),
            value = TextFieldValue(
                text = textState,
                selection = TextRange(textState.length)
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = backgroundColor,
                cursorColor = textColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = textColor,
                fontSize = 16.sp,
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal
            ),
            onValueChange = {
                if (textLimit >= it.text.length) {
                    if (it.text.replace(" ", "").isDigitsOnly()) {
                        textState = it.text.replace(" ", "").moneyType()
                        onChangeListener.invoke(
                            if (textState.isNotEmpty())
                                textState.replace(" ", "").toLong()
                            else 0
                        )
                    }
                }
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 16.sp,
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    color = TextFieldHintColor
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color_E8)
        )

        Box(
            modifier = Modifier
                .width(55.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.str_UZS),
                fontFamily = MainFont,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color_BD
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerInfoTextField(
    modifier: Modifier,
    hint: String,
    text: String,
    backgroundColor: Color,
    strokeColor: Color,
    textColor: Color,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isEditable: Boolean = true,
    onChangeListener: (text: String) -> Unit
) {
    var textState by remember {
        mutableStateOf(text)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(8.dp)
            )
            .height(height = 55.dp)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(100.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier
                    .padding(start = PADDING_VALUE),
                text = hint,
                fontFamily = MainFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color_66
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color_E8)
        )

        TextField(
            modifier = modifier
                .weight(1f),
            value = textState,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = backgroundColor,
                cursorColor = textColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            textStyle = TextStyle(
                color = textColor,
                fontSize = 14.sp,
                fontFamily = MainFont,
                fontWeight = FontWeight.SemiBold
            ),
            onValueChange = {
                textState = it
                onChangeListener.invoke(textState)
            },
            singleLine = true,
            enabled = isEditable,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    SimpleTextField(
        modifier = Modifier
            .height(100.dp),
        hint = "",
        text = "",
        backgroundColor = TextFieldFillColor,
        strokeColor = Color_E8,
        textColor = ItemTextColor,
        singleLine = false
    ) {

    }
}