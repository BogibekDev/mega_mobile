package uz.nlg.mega.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.screens.bottom.ChequesScreen
import uz.nlg.mega.screens.bottom.CustomersScreen
import uz.nlg.mega.screens.bottom.OrdersScreen
import uz.nlg.mega.screens.bottom.ProductsScreen
import uz.nlg.mega.screens.bottom.ProfileScreen
import uz.nlg.mega.screens.bottom.isShowDialog
import uz.nlg.mega.ui.theme.DialogBackgroundColor
import uz.nlg.mega.utils.ScreenID
import uz.nlg.mega.utils.ScreensList
import uz.nlg.mega.views.BottomNavItem


var homeScreenState = ScreenID.ChequesScreen

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator? = null
) {

    var screenID by remember {
        mutableStateOf(homeScreenState)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 1.dp,
                        spotColor = Color.Gray
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in ScreensList) {
                    BottomNavItem(
                        item = i,
                        isSelected = i.id == screenID
                    ) {
                        homeScreenState = i.id
                        screenID = i.id
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
        ) {

            when(screenID) {
                ScreenID.ChequesScreen -> {
                    ChequesScreen(
                        navigator = navigator!!
                    )
                }

                ScreenID.OrdersScreen -> {
                    OrdersScreen(
                        navigator = navigator!!
                    )
                }

                ScreenID.ProductsScreen -> {
                    ProductsScreen()
                }

                ScreenID.CustomersScreen -> {
                    CustomersScreen()
                }

                ScreenID.ProfileScreen -> {
                    ProfileScreen(
                        navigator = navigator!!
                    )
                }
            }
        }

        if (isShowDialog.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DialogBackgroundColor)
            )
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}