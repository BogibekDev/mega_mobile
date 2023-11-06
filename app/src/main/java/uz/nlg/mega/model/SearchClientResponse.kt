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
    val results: ArrayList<SearchedClient>
):Serializable

data class SearchedClient(
    @SerializedName("balance")
    val balance: Long,
    @SerializedName("extra_info")
    val extraInfo: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
):Serializable
