package uz.nlg.mega.data.repository

import uz.nlg.mega.data.remote.ApiService
import uz.nlg.mega.model.Login
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun userLogin(username: String, password: String) =
        apiService.userLogin(Login(username, password))

}