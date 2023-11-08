package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun getCategories(page: Int) = apiService.getCategories(page)
    suspend fun getProducts(search: String, page: Int, ordering: String) =
        apiService.getProducts(search = search, page = page, ordering = ordering)

    suspend fun getSubcategories(categoryId: Int) = apiService.getSubcategories(categoryId)
    suspend fun getProductsBySubcategory(subcategoryId: Int) =
        apiService.getProductsBySubcategory(subcategoryId)

}