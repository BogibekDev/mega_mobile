package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class SearchClientRepository @Inject constructor(private val apiService: ApiServiceWithAuth) {
    suspend fun searchClient(
        search: String,
        isDebt: Boolean?,
        page: Int
    ) = apiService.searchClient(
        search = search,
        isDebt = isDebt,
        page = page,
    )
}