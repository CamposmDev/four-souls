package io.github.camposmdev.foursouls.model.net.response

data class CreateUserRes (
    val id: String,
    val username: String
)

data class LoginUserRes (
    val id: String,
    val username: String
)

data class GetUserByIdRes(
    val id: String,
    val username: String,
    val role: String,
    val createdAt: String,
    val updatedAt: String
)