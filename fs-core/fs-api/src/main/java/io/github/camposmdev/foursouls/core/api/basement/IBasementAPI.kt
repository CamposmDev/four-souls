package io.github.camposmdev.foursouls.core.api.basement

import io.vertx.core.Future

interface IBasementAPI {
    fun chat(username: String?, message: String): Future<Void>
    fun done(chestId: String): Future<Void>
    fun leave(): Future<Void>
}