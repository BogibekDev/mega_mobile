package uz.nlg.mega.model

data class LoginResponse(
    val access: String?,
    val refresh: String?,
    val user_id: Int?,
    val message: String?
)
