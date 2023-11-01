package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName

data class Pagination <T> (
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: ArrayList<T>?
)