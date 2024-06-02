package org.camposmdev.model.context.store

interface IStore<T> {
    fun getState(): T
    fun setState(state: T)
}