package io.github.camposmdev.foursouls.model.context.store.state

import io.github.camposmdev.foursouls.model.api.basement.BasementUser
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject

class BasementState {
    var host: Boolean = false
    var users: List<BasementUser> = listOf()
    val subs: MutableList<MessageConsumer<JsonObject>> = mutableListOf()
}