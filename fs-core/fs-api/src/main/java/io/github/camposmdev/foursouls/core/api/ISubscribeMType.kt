package io.github.camposmdev.foursouls.core.api

import io.github.camposmdev.foursouls.core.api.message.MType
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject

interface ISubscribeMType {
    fun subscribeTo(mtype: MType): MessageConsumer<JsonObject>
}