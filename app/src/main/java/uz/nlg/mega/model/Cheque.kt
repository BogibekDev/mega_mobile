package uz.nlg.mega.model

import uz.nlg.mega.utils.ChequeType

data class Cheque(
    val type: ChequeType,
    val serialNumber: Int,
    val clientName: String,
    val date: String,
    val time: String,
    val products: ArrayList<Product>,
    val totalPrice: Long
)


