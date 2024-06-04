package io.github.camposmdev.foursouls.model.net.api

import io.github.camposmdev.foursouls.model.net.message.MType
import io.github.camposmdev.foursouls.model.net.message.payload.BasementPayload
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.json.JsonObject

interface BasementWSClient {
    fun subscribeTo(mtype: MType): MessageConsumer<BasementPayload>
    fun textMessageHandler(text: String)
    fun closeHandler(arg0: Void?)
    fun decodeMessage(mtype: MType, payload: JsonObject)
    fun sendChatMessage(username: String?, message: String)
    fun sendFinishedMessage()
    fun sendLeaveMessage()
}