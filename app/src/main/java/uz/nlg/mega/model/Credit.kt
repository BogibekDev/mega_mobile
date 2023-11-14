package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("client")
    var client: Int?,
    @SerializedName("payments")
    val payments: ArrayList<ChequePayment>,
    @SerializedName("total_price")
    val totalPrice: Long
)
