package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cheque(
    @SerializedName("cheque_sum")
    val chequeSum: Long,
    @SerializedName("client")
    val client: Client?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("items_count")
    val itemsCount: Int,
    @SerializedName("seller")
    val seller: Seller?,
    @SerializedName("serial_number")
    val serialNumber: Int,
    @SerializedName("status")
    val status: String
) : Serializable

data class PendingCheque(
    val cheque: Int
)
