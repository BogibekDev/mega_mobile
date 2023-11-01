package uz.nlg.mega.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import uz.nlg.mega.MainActivity
import uz.nlg.mega.activity.ui.theme.MEGATheme
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.local.SharedPrefs
import uz.nlg.mega.screens.LoginScreen
import uz.nlg.mega.utils.IsSignedIn
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSignIn = SharedPrefs(this).getBoolean(IsSignedIn)

            if (isSignIn) {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            } else {
                LoginScreen()
            }
        }
    }
}