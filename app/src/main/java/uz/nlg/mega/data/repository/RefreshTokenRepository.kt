package uz.nlg.mega.data.repository

import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.remote.ApiService
import uz.nlg.mega.model.Refresh
import uz.nlg.mega.utils.RefreshToken
import javax.inject.Inject

class RefreshTokenRepository @Inject constructor(
    private val apiService: ApiService,
    private val securePrefs: SecurePrefs
) {
    suspend fun refreshToken() =
        apiService.refreshToken(Refresh(refresh = securePrefs.getString(RefreshToken)!!))
}