package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Seller(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
) : Serializable