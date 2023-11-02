package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("coefficient")
    val coefficient: String,
    @SerializedName("first_quantity_type")
    val firstQuantityType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("second_quantity_type")
    val secondQuantityType: String
): Serializable