package org.camposmdev.model.net

import java.util.*
import java.util.function.Predicate

open class ClientRegistry<T: WSClient> {
    protected val clients: MutableList<T> by lazy {
        Collections.synchronizedList(LinkedList())
    }

    fun add(client: T): Boolean {
        println("Registered Client[${client.id()}]")
        return clients.add(client)
    }

    fun remove(filter: Predicate<T>): Boolean {
        return clients.removeIf(filter);
    }

    fun size(): Int {
        return clients.size
    }

    fun isEmpty(): Boolean {
        return clients.size <= 0
    }
}