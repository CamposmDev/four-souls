package io.github.camposmdev.foursouls.core.api.chest

import io.vertx.core.Future

interface IChestAPI {
    fun chat(username: String?, message: String): Future<Void>
    fun done(chestId: String): Future<Void>
    fun leave(): Future<Void>
}