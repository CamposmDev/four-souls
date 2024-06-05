package io.github.camposmdev.foursouls.model.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageRes(
    @JsonProperty("message")
    val message: String
)