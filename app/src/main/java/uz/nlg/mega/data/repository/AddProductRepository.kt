package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.CartAddProduct
import javax.inject.Inject

class AddProductRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun addProductToCheque(product: CartAddProduct) = apiService.addProductToCart(product)
}