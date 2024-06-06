package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.context.store.IStore

interface IContext<A, B: IStore<A>> {
    fun state(): A
    fun store(): B
}