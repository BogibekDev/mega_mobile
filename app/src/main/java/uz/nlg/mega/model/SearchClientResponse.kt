package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchClientResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: ArrayList<Client>
) : Serializable

data class Client(
    @SerializedName("balance")
    val balance: Long?,
    @SerializedName("extra_info")
    var extraInfo: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("phone_number")
    var phoneNumber: String
) : Serializable
