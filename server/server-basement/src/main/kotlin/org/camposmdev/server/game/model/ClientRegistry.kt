package org.camposmdev.server.game.model

import org.camposmdev.server.game.vertx.WSClient
import java.util.*
import java.util.function.Predicate

object ClientRegistry {
    private val clients: MutableList<WSClient> by lazy {
        Collections.synchronizedList(LinkedList())
    }

    fun add(client: WSClient): Boolean {
        println("Registered Client[${client.id}]")
        return clients.add(client)
    }

    fun remove(filter: Predicate<WSClient>): Boolean {
        return clients.removeIf(filter);
    }

    fun size(): Int {
        return clients.size
    }
}