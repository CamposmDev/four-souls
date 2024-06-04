package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.IStore

interface IContext<A, B: IStore<A>> {
    fun state(): A
    fun store(): B
}