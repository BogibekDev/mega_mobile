package uz.nlg.mega.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import uz.nlg.mega.ui.theme.Color_F6

@Composable
fun ChequeDetailsScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    )

}

@Preview
@Composable
fun ChequeDetailsScreenPreview() {
    ChequeDetailsScreen()
}