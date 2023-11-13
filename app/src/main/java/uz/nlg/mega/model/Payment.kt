package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Payment(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("payment_type")
    val paymentType: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("client")
    val client: Client?,
    @SerializedName("seller")
    val seller: Seller?,
    @SerializedName("serial_number")
    val serialNumber: Int?
) : Serializable