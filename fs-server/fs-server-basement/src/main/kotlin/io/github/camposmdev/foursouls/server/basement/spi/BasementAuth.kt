package io.github.camposmdev.foursouls.server.basement.spi

import io.github.camposmdev.foursouls.core.context.store.MomStore
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx

/**
 * Verifies userId cookie is a valid userId
 */
class BasementAuth(v: Vertx, host: String, port: Int) {
    private val store: MomStore = MomStore(v, host, port)

    fun verifyId(userId: String): Future<String> {
        val promise = Promise.promise<String>()
        store.getUserById(userId).onSuccess {
            promise.complete(it.username)
        }.onFailure {
            promise.fail(it.message)
        }
        return promise.future()
    }
}