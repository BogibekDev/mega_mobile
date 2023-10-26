package uz.nlg.mega.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uz.nlg.mega.R
import uz.nlg.mega.model.BottomNav

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

sealed class Filter(val title: Int?) {
    data object Qarzdorlar: Filter(title = R.string.str_debtors)
    data object Haqdorlar: Filter(title = R.string.str_loaners)
    data object InAscendingOrder: Filter(title = R.string.str_ascending)
    data object InDescendingOrder: Filter(title = R.string.str_descending)

    object None : Filter(title = null)
}

enum class ProductSearchType {
    ByCategory,
    ByMoreSold,
    None
}

sealed class ChequeType(val title: Int?) {
    data object Saved: ChequeType(title = R.string.str_saved)
    data object Paid: ChequeType(title = R.string.str_paid)
    data object Returned: ChequeType(title = R.string.str_returned)
    data object None: ChequeType(title = null)
}