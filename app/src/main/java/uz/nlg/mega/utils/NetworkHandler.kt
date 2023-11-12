package uz.nlg.mega.utils

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import uz.nlg.mega.data.local.SecurePrefs
import uz.nlg.mega.data.repository.RefreshTokenRepository
import uz.nlg.mega.model.ErrorResponse

class NetworkHandler<R, E>(
    private val request: Response<R>,
    private val error: Class<E>
) {

    fun handleSuccess(
        vararg except: Int,
        handler: (R?) -> Unit
    ) {
        if (request.code() in 200..299 && !except.contains(request.code())) {
            handler.invoke(request.body())
        }
    }

    fun handleFailure(
        vararg except: Int,
        handler: (E?) -> Unit
    ) {
        if (request.code() in 400..499 && !except.contains(request.code())) {
            val errorResponse = Gson().fromJson(request.errorBody()!!.string(), error)
            handler.invoke(errorResponse)
        }
    }

    fun handleServerError(
        handler: (code: Int) -> Unit
    ) {
        if (request.code() in 500..599) {
            handler.invoke(request.code())
        }
    }

    fun handleBySuccessStatusCode(
        code: Int,
        handler: (R?) -> Unit
    ) {
        if (code in 200..299)
            if (request.code() == code) handler.invoke(request.body()!!)
    }

    fun handleByFailureStatusCode(
        code: Int,
        handler: (E?) -> Unit
    ) {
        if (code in 400..499)
            if (request.code() == code) handler.invoke(
                Gson().fromJson(
                    request.errorBody()!!.string(), error
                )
            )

    }

    suspend fun handleRefreshToken(
        scope: CoroutineScope,
        handler: suspend CoroutineScope.() -> Unit
    ) {
        if (request.code() == 401) handler.invoke(scope)
    }

}

suspend fun refreshToken(
    repository: RefreshTokenRepository,
    securePrefs: SecurePrefs,
    onResult: (Boolean) -> Unit
) {
    val handler = NetworkHandler(repository.refreshToken(), ErrorResponse::class.java)
    handler.handleSuccess {
        securePrefs.saveString(AccessToken, it!!.access)
        securePrefs.saveString(RefreshToken, it.refresh)
        onResult.invoke(true)
    }
    handler.handleFailure {
        onResult.invoke(false)
    }
    handler.handleServerError {
        onResult.invoke(false)
    }
}