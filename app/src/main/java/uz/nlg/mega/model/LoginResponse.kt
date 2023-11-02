package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String

)
