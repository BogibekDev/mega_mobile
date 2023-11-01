package uz.nlg.mega.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.nlg.mega.ui.theme.DialogMoreBackgroundColor

@Composable
fun LoadingView() {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(DialogMoreBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}