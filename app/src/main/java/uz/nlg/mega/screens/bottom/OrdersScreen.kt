package uz.nlg.mega.screens.bottom

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import uz.nlg.mega.R
import uz.nlg.mega.screens.destinations.AddProductScreenDestination
import uz.nlg.mega.screens.destinations.HomeScreenDestination
import uz.nlg.mega.screens.destinations.LoginScreenDestination
import uz.nlg.mega.screens.destinations.PaymentScreenDestination
import uz.nlg.mega.ui.theme.DarkBlueMainColor
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.RedTextColor
import uz.nlg.mega.utils.Cheques
import uz.nlg.mega.utils.MainFont
import uz.nlg.mega.utils.OrderProducts
import uz.nlg.mega.utils.screenNavigate
import uz.nlg.mega.views.OrderProductItem
import uz.nlg.mega.views.SimpleTopSection

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrdersScreen(
    navigator: DestinationsNavigator
) {

    var isMoreOpen by remember {
        mutableStateOf(false)
    }

//    OrderProducts.clear()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            SimpleTopSection(
                title = stringResource(id = R.string.str_order),
                name = "Ogabek Matyakubov"
            )

            if (OrderProducts.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 65.dp)
                    ) {
                        items(
                            items = OrderProducts,
                            key = { item ->
                                item.id
                            }
                        ) {item ->
                            RevealSwipe(
                                modifier = Modifier,
                                directions = setOf(
                                    RevealDirection.EndToStart
                                ),
                                hiddenContentEnd = {
                                    Icon(
                                        modifier = Modifier
                                            .padding(end = 20.dp),
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                },
                                onBackgroundEndClick = {

                                },
                                backgroundCardEndColor = RedTextColor,
                                animateBackgroundCardColor = true,
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                OrderProductItem(product = item)
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(75.dp, 75.dp),
                        painter = painterResource(id = R.drawable.empty_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        if (OrderProducts.isNotEmpty()) Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .background(DarkBlueMainColor),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = stringResource(id = R.string.str_total_price) + " ${80000}",
                    fontFamily = MainFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {

                AnimatedVisibility(visible = isMoreOpen) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.str_return),
                                fontFamily = MainFont,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = ItemTextColor
                            )

                            Box(
                                modifier = Modifier
                                    .padding(end = 47.dp)
                                    .shadow(
                                        elevation = 25.dp,
                                        shape = CircleShape,
                                        spotColor = Color.Gray
                                    )
                                    .size(34.dp, 34.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable {

                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp, 16.dp),
                                    painter = painterResource(id = R.drawable.ic_return),
                                    contentDescription = null,
                                    tint = MainColor
                                )
                            }

                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.str_save_temp),
                                fontFamily = MainFont,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = ItemTextColor
                            )

                            Box(
                                modifier = Modifier
                                    .padding(end = 47.dp)
                                    .shadow(
                                        elevation = 25.dp,
                                        shape = CircleShape,
                                        spotColor = Color.Gray
                                    )
                                    .size(34.dp, 34.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable {

                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp, 16.dp),
                                    painter = painterResource(id = R.drawable.ic_save_temp),
                                    contentDescription = null,
                                    tint = MainColor
                                )
                            }

                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.str_pay),
                                fontFamily = MainFont,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = ItemTextColor
                            )

                            Box(
                                modifier = Modifier
                                    .padding(end = 47.dp)
                                    .shadow(
                                        elevation = 25.dp,
                                        shape = CircleShape,
                                        spotColor = Color.Gray
                                    )
                                    .size(34.dp, 34.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable {
                                        navigator.screenNavigate(PaymentScreenDestination(Cheques.first()))
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp, 16.dp),
                                    painter = painterResource(id = R.drawable.ic_pay),
                                    contentDescription = null,
                                    tint = MainColor
                                )
                            }

                        }

                        Row(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.str_add_product),
                                fontFamily = MainFont,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = ItemTextColor
                            )

                            Box(
                                modifier = Modifier
                                    .padding(end = 47.dp)
                                    .shadow(
                                        elevation = 25.dp,
                                        shape = CircleShape,
                                        spotColor = Color.Gray
                                    )
                                    .size(34.dp, 34.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable {
                                        navigator.screenNavigate(AddProductScreenDestination)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp, 16.dp),
                                    painter = painterResource(id = R.drawable.ic_add_product),
                                    contentDescription = null,
                                    tint = MainColor
                                )
                            }

                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(end = 38.dp)
                        .padding(bottom = 38.dp)
                        .padding(top = 20.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = CircleShape,
                            spotColor = Color.Gray
                        )
                        .size(52.dp, 52.dp)
                        .clip(CircleShape)
                        .background(MainColor)
                        .clickable {
                            if (OrderProducts.isNotEmpty()) {
                                isMoreOpen = !isMoreOpen
                            } else {
                                navigator.screenNavigate(AddProductScreenDestination)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(if (isMoreOpen) 45F else 0F)
                            .size(24.dp, 24.dp),
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

    }
}
