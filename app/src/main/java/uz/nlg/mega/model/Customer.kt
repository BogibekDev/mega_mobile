package uz.nlg.mega.model

import java.io.Serializable

data class Customer(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val priceDiff: Long
): Serializable
