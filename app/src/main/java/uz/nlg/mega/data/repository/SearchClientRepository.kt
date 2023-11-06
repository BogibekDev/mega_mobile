package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class SearchClientRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {
    suspend fun searchClient(search: String, page: Int=1) = apiService.searchClient(search, page)
}