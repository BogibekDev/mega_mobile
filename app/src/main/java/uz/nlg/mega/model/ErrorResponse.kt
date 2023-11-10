package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val detail: String?,
    val code: String?,
    val message: String?,
    val error: String?,
    @SerializedName("phone_number")
    val phoneNumber: ArrayList<String> = arrayListOf(),
)