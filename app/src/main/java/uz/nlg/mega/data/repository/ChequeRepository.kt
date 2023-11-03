package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class ChequeRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {

    suspend fun getCheques(status: String, page: Int) = apiService.getAllCheques(status, page)

    suspend fun deleteCheque(id: Int) = apiService.deleteCheque(id)
}