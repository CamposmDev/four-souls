package io.github.camposmdev.foursouls.core.context.store

interface IStore<T> {
    fun state(): T
}