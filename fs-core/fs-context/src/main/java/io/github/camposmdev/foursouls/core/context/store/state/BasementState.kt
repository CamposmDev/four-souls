package io.github.camposmdev.foursouls.core.context.store.state

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.BasementPayload
import io.vertx.core.eventbus.MessageConsumer

class BasementState {
    var connected: Boolean = false
    var host: Boolean = false
    var username: String? = null
    var users = listOf<BasementUser>()
    var subs = mutableListOf<MessageConsumer<BasementPayload>>()
    var chestId: String? = null
    var chat = mutableListOf<BasementChat>()
    var message: String? = null
}