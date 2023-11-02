package uz.nlg.mega.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.nlg.mega.model.Login
import uz.nlg.mega.model.LoginResponse
import uz.nlg.mega.model.Refresh

interface ApiService {

    @POST("auth/login/")
    suspend fun userLogin(
        @Body data: Login
    ): Response<LoginResponse>

    @POST("auth/login/refresh/")
    suspend fun refreshToken(
        @Body data: Refresh
    ): Response<LoginResponse>

}