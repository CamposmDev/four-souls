package io.github.camposmdev.foursouls.model.context.store

interface IStore<T> {
    fun state(): T
    fun setState(state: T)
}