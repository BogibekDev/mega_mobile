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