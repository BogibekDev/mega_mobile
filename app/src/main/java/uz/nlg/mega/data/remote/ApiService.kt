package uz.nlg.mega.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.nlg.mega.model.Login
import uz.nlg.mega.model.LoginResponse

interface ApiService {

    @POST("auth/login/")
    suspend fun userLogin(
        @Body login: Login
    ): Response<LoginResponse>



}