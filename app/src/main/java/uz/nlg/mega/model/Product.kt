package uz.nlg.mega.model

import java.io.Serializable

data class Product (
    val id: Int,
    var name: String,
    var quantity: Int,
    var firstType: String,
    var secondType: String,
    var price: Long
): Serializable