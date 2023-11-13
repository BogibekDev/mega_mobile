package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Client(
    @SerializedName("balance")
    val balance: Long?,
    @SerializedName("extra_info")
    var extraInfo: String?,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("phone_number")
    var phoneNumber: String?
) : Serializable