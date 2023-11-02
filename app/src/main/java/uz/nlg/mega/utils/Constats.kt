package uz.nlg.mega.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.nlg.mega.R
import uz.nlg.mega.model.BottomNav
import java.io.Serializable

val MainFont = FontFamily(
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.medium, FontWeight.Medium)
)

val PADDING_VALUE = 16.dp

enum class ScreenID {
    ChequesScreen,
    OrdersScreen,
    ProductsScreen,
    CustomersScreen,
    ProfileScreen
}

var ScreensList = listOf(
    BottomNav(
        id = ScreenID.ChequesScreen,
        route = "cheque",
        icon = R.drawable.cheques,
        title = R.string.str_cheques,
    ),
    BottomNav(
        id = ScreenID.OrdersScreen,
        route = "orders",
        icon = R.drawable.orders,
        title = R.string.str_orders
    ),
    BottomNav(
        id = ScreenID.ProductsScreen,
        route = "products",
        icon = R.drawable.products,
        title = R.string.str_products
    ),
    BottomNav(
        id = ScreenID.CustomersScreen,
        route = "customers",
        icon = R.drawable.customers,
        title = R.string.str_customers
    ),
    BottomNav(
        id = ScreenID.ProfileScreen,
        route = "profile",
        icon = R.drawable.profile,
        title = R.string.str_profile
    )
)

sealed class FilterType(val title: Int?) {
    data object Qarzdorlar: FilterType(title = R.string.str_debtors)
    data object Haqdorlar: FilterType(title = R.string.str_loaners)
    data object InAscendingOrder: FilterType(title = R.string.str_ascending)
    data object InDescendingOrder: FilterType(title = R.string.str_descending)

    object None : FilterType(title = null)
}

enum class ProductSearchType {
    ByCategory,
    ByMoreSold,
    None
}

sealed class ChequeType(val status: String): Serializable {
    data object Pending: ChequeType("pending")
    data object Done: ChequeType("done")
    data object Returned: ChequeType("returned")
    data object None: ChequeType("")
}

sealed class PaymentType(val title: Int): Serializable {
    data object Cash: PaymentType(title = R.string.str_cash)
    data object Terminal: PaymentType(title = R.string.str_terminal)
    data object OnlinePayment: PaymentType(title = R.string.str_online_payment)
    data object Credit: PaymentType(title = R.string.str_credit)
}

enum class CreditType: Serializable {
    Daily,
    Monthly
}

const val AccessToken = "access"
const val RefreshToken = "refresh"
const val IsSignedIn = "isSignedIn"
const val ProfileName = "profileName"