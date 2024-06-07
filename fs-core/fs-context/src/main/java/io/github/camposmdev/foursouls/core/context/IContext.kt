package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.context.store.IStore
import io.vertx.core.eventbus.MessageConsumer

interface IContext<T, U: IStore<T>> {
    fun state(): T
    fun store(): U
    fun subscribe(): MessageConsumer<T>
}