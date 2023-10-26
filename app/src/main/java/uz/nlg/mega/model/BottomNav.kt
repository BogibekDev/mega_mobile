package uz.nlg.mega.model

import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.painter.Painter
import uz.nlg.mega.utils.ScreenID

data class BottomNav(
    var id: ScreenID,
    var route: String,
    var icon: Int,
    var title: Int
)
