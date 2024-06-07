package io.github.camposmdev.foursouls.core.api.message.payload

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.camposmdev.foursouls.core.api.chest.ChestUser

interface ChestPayload : Payload

data class ChestGreeting(
    @JsonProperty("host")
    val host: Boolean,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("users")
    val users: List<ChestUser>
) : ChestPayload {
    @JsonProperty("message")
    val message: String = "Joined Chest"
}

data class ChestChat(
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("message")
    val message: String
) : ChestPayload

data class ChestDone(
    @JsonProperty("chestId")
    val chestId: String
) : ChestPayload

data class ChestUsers(
    @JsonProperty("users")
    val users: List<ChestUser>
) : ChestPayload

data class ChestClosed(
    @JsonProperty("message")
    val message: String
): ChestPayload

data class ChestError(
    @JsonProperty("message")
    val message: String
) : ChestPayload

data class ChestLeave(
    @JsonProperty("message")
    val message: String = "Goodbye Cruel World"
) : ChestPayload