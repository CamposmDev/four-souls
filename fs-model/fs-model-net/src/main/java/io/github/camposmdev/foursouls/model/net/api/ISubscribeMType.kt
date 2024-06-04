package io.github.camposmdev.foursouls.model.net.api

import io.github.camposmdev.foursouls.model.net.message.MType
import io.github.camposmdev.foursouls.model.net.message.payload.Payload
import io.vertx.core.eventbus.MessageConsumer

interface ISubscribeMType<T : Payload> {
    fun subscribeTo(mtype: MType): MessageConsumer<T>
}