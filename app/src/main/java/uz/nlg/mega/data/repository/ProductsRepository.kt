package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun getCategories(page: Int) = apiService.getCategories(page)
    suspend fun getProducts(search: String, page: Int, ordering: String) = apiService.getProducts(search = search, page = page, ordering = ordering)

}