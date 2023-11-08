package uz.nlg.mega.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("products_count")
    val productsCount: Int,
    @SerializedName("subcategories_count")
    val subcategoriesCount: Int
)

data class Subcategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("products_count")
    val productsCount: Int
)