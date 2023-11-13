package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.CartClient
import javax.inject.Inject

class AddCustomerRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun getCustomers(search: String, ordering: String?, page: Int) = apiService.searchClient(
        search = search,
        ordering = ordering,
        page = page
    )
    suspend fun addCustomerToCart(id: Int) = apiService.addCustomer(CartClient(id))
}