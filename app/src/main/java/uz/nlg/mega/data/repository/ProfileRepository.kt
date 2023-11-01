package uz.nlg.mega.data.repository

import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.remote.ApiServiceWithAuth
import uz.nlg.mega.model.LogOut
import uz.nlg.mega.utils.RefreshToken
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiServiceWithAuth: ApiServiceWithAuth,
    private val securePrefs: SecurePrefs
) {

    suspend fun getProfile() = apiServiceWithAuth.getProfile()

    suspend fun logOut() = apiServiceWithAuth.profileLogOut(LogOut(securePrefs.getString(RefreshToken) ?: ""))

}