package io.github.camposmdev.foursouls.model.net.message.payload

import io.github.camposmdev.foursouls.model.net.BasementUser

interface BasementPayload : Payload

data class BasementGreeting(
    val message: String,
    val host: Boolean,
    val users: List<BasementUser>
) : BasementPayload

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

