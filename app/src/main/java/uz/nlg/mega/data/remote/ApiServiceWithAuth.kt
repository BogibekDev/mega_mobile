package uz.nlg.mega.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.nlg.mega.model.LogOut
import uz.nlg.mega.model.Profile

interface ApiServiceWithAuth {

    @GET("profile/")
    suspend fun getProfile(): Response<Profile>

    @POST("auth/logout/")
    suspend fun profileLogOut(
        @Body logOut: LogOut
    ): Response<Unit>

}