package uz.nlg.mega

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.DestinationsNavHost
import uz.nlg.mega.screens.HomeScreen
import uz.nlg.mega.screens.LoginScreen
import uz.nlg.mega.screens.NavGraphs
import uz.nlg.mega.screens.destinations.HomeScreenDestination
import uz.nlg.mega.screens.destinations.LoginScreenDestination
import uz.nlg.mega.ui.theme.MEGATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}