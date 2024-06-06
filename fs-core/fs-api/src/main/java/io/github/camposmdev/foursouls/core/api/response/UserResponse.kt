package io.github.camposmdev.foursouls.core.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserRes (
    @JsonProperty("id")
    val id: String,
    @JsonProperty("username")
    val username: String
)

data class LoginUserRes(
    @JsonProperty("id")
    var id: String,
    @JsonProperty("username")
    var username: String
)

data class GetUserByIdRes(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("role")
    val role: String,
    @JsonProperty("createdAt")
    val createdAt: String,
    @JsonProperty("updatedAt")
    val updatedAt: String
)