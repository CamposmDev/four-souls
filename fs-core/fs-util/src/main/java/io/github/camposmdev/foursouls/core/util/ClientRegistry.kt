package io.github.camposmdev.foursouls.core.util

import java.util.*
import java.util.function.Predicate

open class ClientRegistry<T: IServerWSManager> {
    protected val clients: MutableList<T> by lazy {
        Collections.synchronizedList(LinkedList())
    }

    open fun add(client: T): Boolean {
        return clients.add(client)
    }

    fun remove(filter: Predicate<T>): Boolean {
        return clients.removeIf(filter)
    }

    fun size(): Int {
        return clients.size
    }

    fun isEmpty(): Boolean {
        return clients.size <= 0
    }
}