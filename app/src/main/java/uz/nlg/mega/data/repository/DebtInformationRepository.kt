package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiServiceWithAuth
import javax.inject.Inject

class DebtInformationRepository @Inject constructor(
    private val apiService: ApiServiceWithAuth
) {

    suspend fun getDebtByDayOrMonth(
        search:String,
        sellerId: String,
        from: String,
        to: String,
        page: Int
    ) = apiService.getDebtByDayOrMonth(
        search=search,
        seller = sellerId,
        createdAtMin = from,
        createdAtMax = to,
        page = page
    )
}