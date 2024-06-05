package io.github.camposmdev.foursouls.model.api

import io.github.camposmdev.foursouls.model.api.message.MType
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject

interface ISubscribeMType {
    fun subscribeTo(mtype: MType): MessageConsumer<JsonObject>
}