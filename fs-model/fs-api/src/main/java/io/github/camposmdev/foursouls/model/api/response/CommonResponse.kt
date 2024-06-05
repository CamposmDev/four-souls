package io.github.camposmdev.foursouls.model.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageRes(
    @JsonProperty("message")
    val message: String
)

data class Message500Res(
    @JsonProperty("statusCode")
    val statusCode: Int,
    @JsonProperty("error")
    val error: String,
    @JsonProperty("message")
    val message: String
)