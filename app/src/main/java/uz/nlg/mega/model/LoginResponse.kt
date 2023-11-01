package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val access: String,
    val refresh: String,
    @SerializedName("user_id")
    val userId: Int
)
