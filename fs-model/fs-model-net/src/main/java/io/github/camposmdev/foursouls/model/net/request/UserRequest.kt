package io.github.camposmdev.foursouls.model.net.request

data class CreateUserReq (
    val email: String,
    val username: String,
    val password: String
)

data class LoginUserReq (
    val username: String,
    val password: String
)