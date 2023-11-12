package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {
    suspend fun getCart() = apiService.getCart()
    suspend fun deleteItem(id: Int) = apiService.deleteCartItem(id)

}