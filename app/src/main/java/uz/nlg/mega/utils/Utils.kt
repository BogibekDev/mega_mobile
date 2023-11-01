package uz.nlg.mega.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import retrofit2.HttpException
import uz.nlg.mega.R
import uz.nlg.mega.activity.LoginActivity
import uz.nlg.mega.ui.theme.DarkBlueMainColor
import uz.nlg.mega.ui.theme.GreenColor
import uz.nlg.mega.ui.theme.ItemTextColor
import uz.nlg.mega.ui.theme.MainColor
import uz.nlg.mega.ui.theme.RedTextColor


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


fun typeColor(type: String) = when (type) {
    ChequeType.Pending.status -> DarkBlueMainColor
    ChequeType.Done.status -> GreenColor
    else -> RedTextColor
}

fun printError(e: HttpException) {

    val TAG = "->->->->-> ERROR <-<-<-<-<-"

    Log.w(TAG, "->->->->->->->->->->->->->->-> ERROR HAPPENED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")
    Log.e(TAG, "->->->->->->->->->->->->->->-> ERROR OPENED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")

    Log.i(TAG, "->->->->-> ERROR CODE:  ${e.code()} <-<-<-<-<-")
    Log.i(TAG, "->->->->-> ERROR MESSAGE:  ${e.message()} <-<-<-<-<-")
    Log.i(TAG, "->->->->-> ERROR RESPONSE\n${e.response()}\n<-<-<-<-<-")

    Log.e(TAG, "->->->->->->->->->->->->->->-> ERROR CLOSED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")

}

fun printError(e: Exception) {

    val TAG = "->->->->-> ERROR <-<-<-<-<-"

    Log.w(TAG, "->->->->->->->->->->->->->->-> ERROR HAPPENED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")
    Log.e(TAG, "->->->->->->->->->->->->->->-> ERROR OPENED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")

    Log.i(TAG, "->->->->-> ERROR MESSAGE:  ${e.message} <-<-<-<-<-")

    e.printStackTrace()

    Log.e(TAG, "->->->->->->->->->->->->->->-> ERROR CLOSED <-<-<-<-<-<-<-<-<-<-<-<-<-<-<-")

}

fun findChequeType(status: ChequeType) = when (status) {
    ChequeType.Pending -> R.string.str_saved
    ChequeType.Returned -> R.string.str_returned
    ChequeType.Done -> R.string.str_paid
    ChequeType.None -> R.string.str_empty
}

fun findChequeType(status: String) = when (status) {
    ChequeType.Pending.status -> R.string.str_saved
    ChequeType.Returned.status -> R.string.str_returned
    ChequeType.Done.status -> R.string.str_paid
    else -> R.string.str_empty
}


fun dateToString(date: String): String {
//    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
//    val formattedDate = LocalDateTime.parse(date, dateFormatter)
//    return DateTimeFormatter.ofPattern("hh:mm | dd.MM.yyyy").format(formattedDate)

    val day = date.substring(0..9)
    val time = date.subSequence(11..15)
    return "$time | $day"

}

fun navigateToLoginScreen(context: Context) {
    val activity = context as? Activity

    Intent(context, LoginActivity::class.java).apply {
        activity?.startActivity(this)
    }
    activity?.finish()
}