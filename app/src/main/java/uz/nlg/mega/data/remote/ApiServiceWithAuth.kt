package uz.nlg.mega.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import uz.nlg.mega.model.Category
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.ChequeDetailResponse
import uz.nlg.mega.model.Pagination
import uz.nlg.mega.model.Product
import uz.nlg.mega.model.Profile
import uz.nlg.mega.model.Refresh

interface ApiServiceWithAuth {

    @GET("cheques/")
    suspend fun getAllCheques(
        @Query("status") status: String = "",
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 15,
    ): Response<Pagination<Cheque>>


    @DELETE("cheques/{id}/")
    suspend fun deleteCheque(
        @Path("id") id: Int,
    ): Response<Unit>

    @GET("cheques/{id}/")
    suspend fun getChequeById(
        @Path("id") id: Int
    ): Response<ChequeDetailResponse>

    @GET("profile/")
    suspend fun getProfile(): Response<Profile>

    @POST("auth/logout/")
    suspend fun profileLogOut(
        @Body refresh: Refresh
    ): Response<Unit>

    @GET("categories/")
    suspend fun getCategories(): Response<Pagination<Category>>

    @GET("products-for-mobile/")
    suspend fun getProducts(): Response<Pagination<Product>>


}