package uz.nlg.mega.data.remote

import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.Pagination
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.nlg.mega.model.LogOut
import uz.nlg.mega.model.Profile

interface ApiServiceWithAuth {

    @GET("cheques")
    suspend fun getAllCheques(
        @Query("status") status: String = "",
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 15,
    ): Response<Pagination<Cheque>>

    @GET("profile/")
    suspend fun getProfile(): Response<Profile>

    @POST("auth/logout/")
    suspend fun profileLogOut(
        @Body logOut: LogOut
    ): Response<Unit>

}