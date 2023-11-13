package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class DebtHistoryRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {

    suspend fun getPaymentHistoryByClient(id: String, page:Int) = apiService.getPaymentHistoryByClient(id, page)
}