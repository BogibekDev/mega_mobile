package uz.nlg.mega.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nlg.mega.R
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.utils.MainFont

@Composable
fun SimpleTopSection(
    title: String, name: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MainColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            fontFamily = MainFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.White
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = name,
            fontFamily = MainFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.White
        )
    }

}

@Composable
fun BackTopSection(
    title: String, onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MainColor),
    ) {

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp, 24.dp)
                    .clickable {
                        onBackClick.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                fontFamily = MainFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DoneTopSection(
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MainColor),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp, 24.dp)
                    .clickable {
                        onBackClick.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.White
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp, 24.dp)
                    .clickable {
                        onDoneClick.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_done),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ProfileTopSection(
    onLogOutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(MainColor),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(id = R.string.sre_profile),
                fontFamily = MainFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.White
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp, 24.dp)
                    .clickable {
                        onLogOutClick.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_log_out),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun SearchAndFilterTopSection(
    isBack: Boolean,
    isFilter: Boolean = false,
    title: String,
    onBackClick: () -> Unit,
    onFilterClick: (() -> Unit)? = null,
    onEditTextBackClick: (() -> Unit)? = null,
    onEditTextClearClick: (() -> Unit)? = null,
    searchHint:String=stringResource(id = R.string.str_enter_customer_name),
    searchFunction: (text: String) -> Unit
) {

    var isSearching by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(MainColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.height(75.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                if (isBack) Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp, 24.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onBackClick.invoke()
                        },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.White
                )

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = title,
                    fontFamily = MainFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier.height(75.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(24.dp, 24.dp)
                        .clickable {
                            isSearching = true
                        },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.White
                )

                if (isFilter) Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(24.dp, 24.dp)
                        .clickable {
                            onFilterClick!!.invoke()
                        },
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        AnimatedVisibility(
            visible = isSearching,
            enter = slideIn(initialOffset = {
                IntOffset(it.width, 0)
            }),
            exit = slideOut(targetOffset = {
                IntOffset(it.width, 0)
            }),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                contentAlignment = Alignment.Center
            ) {
                SearchTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    hint = searchHint,
                    text = "",
                    onBackClick = {
                        onEditTextBackClick?.invoke()
                        isSearching = false
                    },
                    onChangeListener = searchFunction,
                    onClearClick = onEditTextClearClick
                )
            }
        }
    }

}

@Preview
@Composable
fun TopSectionPreview() {

    Column {
        SimpleTopSection(
            title = "Mijozlar",
            name = "Ogabek Matyakubov"
        )

        Spacer(Modifier.height(100.dp))

        BackTopSection(title = "Mijozlar") {

        }

        Spacer(Modifier.height(100.dp))

        SearchAndFilterTopSection(
            isBack = true,
            isFilter = true,
            title = "Mijozlar",
            onBackClick = {

            },
            onFilterClick = {

            }
        ) {

        }

        Spacer(Modifier.height(100.dp))

        DoneTopSection(onBackClick = {

        }) {

        }

        Spacer(Modifier.height(100.dp))

        ProfileTopSection {
            
        }

    }
}