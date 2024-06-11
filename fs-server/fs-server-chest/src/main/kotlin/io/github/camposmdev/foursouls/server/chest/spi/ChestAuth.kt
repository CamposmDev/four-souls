package io.github.camposmdev.foursouls.server.chest.spi

import io.github.camposmdev.foursouls.core.api.mom.MomAPI
import io.github.camposmdev.foursouls.core.api.response.GetUserByIdRes
import io.github.camposmdev.foursouls.core.api.response.MessageRes
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx

class ChestAuth(v: Vertx, host: String, port: Int) {
    private val api = MomAPI(v, host, port)

    fun verifyId(userId: String): Future<String> {
        val promise = Promise.promise<String>()
        api.getUserById(userId).onSuccess { res ->
            val job = res.body().toJsonObject()
            if (res.statusCode() == 200) {
                val body = job.mapTo(GetUserByIdRes::class.java)
                promise.complete(body.id)
            } else {
                val body = job.mapTo(MessageRes::class.java)
                promise.fail(body.message)
            }
        }.onFailure { promise.fail(it.message) }
        return promise.future()
    }
}
