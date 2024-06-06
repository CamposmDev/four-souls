package io.github.camposmdev.foursouls.core.api

import io.github.camposmdev.foursouls.core.api.message.MType
import io.vertx.core.eventbus.MessageConsumer

interface ISubscribeMType<T> {
    fun subscribeTo(mtype: MType): MessageConsumer<T>
}