package uz.nlg.mega.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import uz.nlg.mega.model.Customer

@Destination
@Composable
fun CustomerInformationScreen(
    navigator: DestinationsNavigator? = null,
    customer: Customer? = null
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {

        }
    }
}

@Preview
@Composable
fun CustomerInformationScreenPreview() {
    CustomerInformationScreen()
}