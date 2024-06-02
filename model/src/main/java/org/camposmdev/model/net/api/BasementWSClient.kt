package org.camposmdev.model.net.api

import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.message.MType

interface BasementWSClient {
    fun textMessageHandler(text: String)
    fun closeHandler(arg0: Void?)
    fun decodeMessage(mtype: MType, payload: JsonObject)
    fun sendChatMessage(username: String?, message: String)
    fun sendFinishedMessage()
    fun sendLeaveMessage()
}