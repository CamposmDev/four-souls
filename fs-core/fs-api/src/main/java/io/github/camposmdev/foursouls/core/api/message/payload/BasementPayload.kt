package io.github.camposmdev.foursouls.core.api.message.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.camposmdev.foursouls.core.api.basement.BasementUser

interface BasementPayload : Payload

data class BasementGreeting(
    @JsonProperty("host")
    val host: Boolean,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("users")
    val users: List<BasementUser>
) : BasementPayload {
    @JsonProperty("message")
    val message: String = "Joined Basement"
}

data class BasementChat(
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("message")
    val message: String
) : BasementPayload

data class BasementDone(
    @JsonProperty("chestId")
    val chestId: String
) : BasementPayload

data class BasementUsers(
    @JsonProperty("users")
    val users: List<BasementUser>,
    val message: String
) : BasementPayload

data class BasementClosed(
    @JsonProperty("message")
    val message: String
): BasementPayload

data class BasementError(
    @JsonProperty("message")
    val message: String
) : BasementPayload

data class BasementLeave(
    @JsonProperty("message")
    val message: String = "Goodbye Cruel World"
) : BasementPayload

