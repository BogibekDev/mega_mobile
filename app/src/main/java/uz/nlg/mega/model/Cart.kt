package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartResponse(
    @SerializedName("client")
    val client: Client?,
    @SerializedName("total_price")
    val totalPrice: Long,
    @SerializedName("cart_items")
    val cartItems: ArrayList<CartItem>
): Serializable

data class CartItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: CartProduct,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("quantity_type")
    val quantityType: String,
    @SerializedName("sold_price")
    val soldPrice: Long
): Serializable

data class CartProduct(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("first_quantity_type")
    val quantityType: String
): Serializable

data class CartClient(
    val client: Int?
)

data class CartAddProduct(
    @SerializedName("product")
    val product: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("quantity_type")
    val quantityType: String,
    @SerializedName("sold_price")
    val soldPrice: Long,
    @SerializedName("id")
    val id: Int = 0
): Serializable

data class Cart(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("client")
    val client: Int?,
    @SerializedName("status")
    var status: String,
    @SerializedName("cheque_items")
    val chequeItems: ArrayList<CartAddProduct>,
    @SerializedName("payments")
    var payments: ArrayList<ChequePayment>
): Serializable