package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.Cart
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun saveCheque(cart: Cart) = apiService.saveCheque(cart)

}