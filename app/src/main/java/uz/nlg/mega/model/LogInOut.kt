package uz.nlg.mega.model

data class Login(
    val username: String,
    val password: String
)

data class Refresh(
    val refresh: String
)