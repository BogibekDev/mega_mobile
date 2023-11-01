package uz.nlg.mega.model

data class ErrorResponse(
    val detail: String?,
    val code: String?,
    val message: String?
)