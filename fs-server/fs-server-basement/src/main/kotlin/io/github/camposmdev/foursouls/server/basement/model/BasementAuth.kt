package io.github.camposmdev.foursouls.server.basement.model

import io.github.camposmdev.foursouls.model.context.store.MomStore
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx

/**
 * Verifies userId cookie is a valid userId
 */
class BasementAuth(v: Vertx, hostName: String, port: Int) {
    private val store = MomStore(v, hostName, port)
    fun verifyUserId(userId: String): Future<String> {
        val promise = Promise.promise<String>()
        store.getUserById(userId).onSuccess {
            promise.complete(it.username)
        }.onFailure {
            promise.fail(it.message)
        }
        return promise.future()
    }
}