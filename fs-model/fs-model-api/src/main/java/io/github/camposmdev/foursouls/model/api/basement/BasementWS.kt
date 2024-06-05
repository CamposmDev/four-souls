package io.github.camposmdev.foursouls.model.api.basement

import io.github.camposmdev.foursouls.model.api.ISubscribeMType
import io.github.camposmdev.foursouls.model.api.message.MType
import io.vertx.core.Future
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject

interface BasementWS : ISubscribeMType {
    override fun subscribeTo(mtype: MType): MessageConsumer<JsonObject>
    fun textMessageHandler(text: String)
    fun closeHandler(arg0: Void?)
    fun decodeMessage(mtype: MType, payload: JsonObject)
    fun sendChatMessage(username: String, message: String)
    fun sendFinishedMessage()
    fun sendLeaveMessage()
    fun connect(host: String, port: Int, userId: String): Future<Void>
}