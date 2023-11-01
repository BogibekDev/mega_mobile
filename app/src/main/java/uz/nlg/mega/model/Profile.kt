package uz.nlg.mega.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profile(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("day_trade")
    val dayTrade: Trade,
    @SerializedName("month_trade")
    val monthTrade: Trade,
    @SerializedName("day_debt")
    val dayDebt: Long,
    @SerializedName("month_debt")
    val monthDebt: Long,
): Serializable

data class Trade(
    val sold: Long,
    val harm: Long,
    val returned: Long
): Serializable
