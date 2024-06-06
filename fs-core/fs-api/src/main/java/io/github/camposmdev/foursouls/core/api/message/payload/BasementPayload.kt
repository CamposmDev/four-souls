package io.github.camposmdev.foursouls.core.api.message.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.camposmdev.foursouls.core.api.basement.BasementUser

interface BasementPayload : Payload

data class BasementGreeting(
    @JsonProperty("host")
    val host: Boolean,
    @JsonProperty("users")
    val users: List<BasementUser>
) : BasementPayload {
    @JsonProperty("message")
    val message: String = "Joined Lobby"
}

data class BasementChat(
    val username: String,
    val message: String
) : BasementPayload

data class BasementDone(
    val chestId: String
) : BasementPayload

data class BasementUsers(
    val users: List<BasementUser>
) : BasementPayload

data class BasementClosed(
    val message: String
): BasementPayload

data class BasementError(
    val message: String
) : BasementPayload

