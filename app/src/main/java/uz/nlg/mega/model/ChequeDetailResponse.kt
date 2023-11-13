package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChequeDetailResponse(
    @SerializedName("cheque_items")
    val chequeItems: ArrayList<ChequeItem>,
    @SerializedName("cheque_sum")
    val chequeSum: Long,
    @SerializedName("client")
    val client: Client,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("items_count")
    val itemsCount: Int,
    @SerializedName("payments")
    val chequePayments: ArrayList<ChequePayment>,
    @SerializedName("seller")
    val seller: Seller,
    @SerializedName("serial_number")
    val serialNumber: Int,
    @SerializedName("status")
    val status: String
)

data class ChequeItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("product")
    val product: ChequeProduct,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("sold_price")
    val soldPrice: Long,
    @SerializedName("total")
    val total: Long
) : Serializable

data class ChequeProduct(
    @SerializedName("first_quantity_type")
    val firstQuantityType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Serializable

data class ChequePayment(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("payment_type")
    val paymentType: String,
    @SerializedName("price")
    val price: Long
) : Serializable