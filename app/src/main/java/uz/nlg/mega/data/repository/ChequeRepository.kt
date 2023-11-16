package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.PendingCheque
import javax.inject.Inject

class ChequeRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {

    suspend fun getCheques(status: String, page: Int) = apiService.getAllCheques(status, page)

    suspend fun deleteCheque(id: Int) = apiService.deleteCheque(id)

    suspend fun addPendingToCart(id: Int) = apiService.addChequeToCart(PendingCheque(id))

}