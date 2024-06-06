package io.github.camposmdev.foursouls.core.api.basement

import io.github.camposmdev.foursouls.core.api.ISignMType
import io.github.camposmdev.foursouls.core.api.ISubscribeMType
import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.payload.BasementPayload
import io.vertx.core.Future
import io.vertx.core.json.JsonObject

interface BasementWS : ISubscribeMType<BasementPayload>, ISignMType {
    fun textMessageHandler(text: String)
    fun closeHandler(arg0: Void?)
    fun decodeMessage(mtype: MType, payload: JsonObject)
    fun chat(username: String?, message: String): Future<Void>
    fun done(chestId: String): Future<Void>
    fun leave(): Future<Void>
    fun connect(host: String, port: Int, userId: String): Future<Void>
    fun sendText(text: String): Future<Void>
}