package io.github.camposmdev.foursouls.core.api.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserReq (
    @JsonProperty("email")
    val email: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)

data class LoginUserReq (
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)