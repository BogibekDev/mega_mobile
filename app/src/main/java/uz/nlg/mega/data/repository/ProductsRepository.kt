package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun getCategories() = apiService.getCategories()
    suspend fun getProducts() = apiService.getProducts()

}