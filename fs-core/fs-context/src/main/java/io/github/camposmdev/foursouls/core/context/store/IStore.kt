package io.github.camposmdev.foursouls.core.context.store

import io.vertx.core.eventbus.MessageConsumer

/**
 * A generic interface for managing state and subscribing to state changes.
 *
 * @param T the type of the state managed by the store.
 */
interface IStore<T> {

    /**
     * Retrieves the current state of the store.
     *
     * @return the current state.
     */
    fun state(): T

    /**
     * Subscribes to state changes.
     * The subscription mechanism is provided by Vert.x's `MessageConsumer`.
     *
     * @return a `MessageConsumer` that can be used to receive updates whenever the state changes.
     */
    fun subscribe(): MessageConsumer<T>
}
