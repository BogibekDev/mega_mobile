package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName

data class Pagination <T> (
    @SerializedName("count")
    var count: Int?,
    @SerializedName("next")
    var next: String?,
    @SerializedName("previous")
    var previous: String?,
    @SerializedName("results")
    val results: ArrayList<T>
)