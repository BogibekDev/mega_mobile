package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class ChequeDetailRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {
    suspend fun getChequeById(id: Int) = apiService.getChequeById(id)
}