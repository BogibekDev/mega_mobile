package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.Client
import javax.inject.Inject

class ClientInfoRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {

    suspend fun addClient(client: Client) = apiService.addClient(client = client)

    suspend fun editClient(id: Int, client: Client) = apiService.editClient(id, client)


}