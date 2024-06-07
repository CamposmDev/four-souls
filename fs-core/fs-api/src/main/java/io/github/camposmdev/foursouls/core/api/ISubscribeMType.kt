package io.github.camposmdev.foursouls.core.api

import io.github.camposmdev.foursouls.core.api.message.payload.Payload
import io.vertx.core.eventbus.MessageConsumer

interface ISubscribeMType<T, U : Payload> {
    fun subscribeTo(mtype: T): MessageConsumer<U>
}