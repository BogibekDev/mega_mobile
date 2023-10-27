package uz.nlg.mega.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import uz.nlg.mega.ui.theme.DarkBlueMainColor
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.RedTextColor
import java.util.Locale


fun Long.moneyType(): String {
    return this.toString().moneyType() + " so'm"
}

fun String.moneyType(): String {
    return this
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
}

fun DestinationsNavigator.screenNavigate(route: Direction) {
    this.navigate(route) {
        popUpTo(popUpToId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun String.forSearchText(search: String): AnnotatedString {

    val splitTexts = this.split(search)

    return buildAnnotatedString {
        var count = splitTexts.size
        for (i in splitTexts.indices) {
            if (i == splitTexts.size - 1)
                if (splitTexts.size == 1) {
                    withStyle(
                        style = SpanStyle(
                            color = ItemTextColor
                        )
                    ) {
                        append(splitTexts[i])
                    }
                    continue
                } else if (splitTexts[i].isEmpty())
                    continue

            if (splitTexts[i].isEmpty()) {
                withStyle(
                    style = SpanStyle(
                        color = MainColor
                    )
                ) {
                    append(search)
                }
                count--
            } else {
                withStyle(
                    style = SpanStyle(
                        color = ItemTextColor
                    )
                ) {
                    append(splitTexts[i])
                }
                if (count > 1) {
                    withStyle(
                        style = SpanStyle(
                            color = MainColor
                        )
                    ) {
                        append(search)
                    }
                }
                count--
            }
        }
    }
}



fun typeColor(type: ChequeType): Color {
    return when (type) {
        ChequeType.Saved -> DarkBlueMainColor
        ChequeType.Paid -> GreenColor
        else -> RedTextColor
    }
}
