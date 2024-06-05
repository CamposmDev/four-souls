package io.github.camposmdev.foursouls.model.api.mom

import io.github.camposmdev.foursouls.model.card.BaseCard
import io.vertx.core.Future
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse

interface MomHttpClient {
    fun registerUser(email: String, username: String, password: String): Future<HttpResponse<Buffer>>
    fun loginUser(username: String, password: String): Future<HttpResponse<Buffer>>
    fun logoutUser(): Future<HttpResponse<Buffer>>
    fun getUserById(id: String): Future<HttpResponse<Buffer>>
    fun hostBasement(): Future<HttpResponse<Buffer>>
    fun joinBasement(basementId: String): Future<HttpResponse<Buffer>>
    fun freeBasement(basementId: String, basementKey: String): Future<HttpResponse<Buffer>>
    fun hostChest(): Future<HttpResponse<Buffer>>
    fun joinChest(chestId: String): Future<HttpResponse<Buffer>>
    fun freeChest(chestId: String, chestKey: String): Future<HttpResponse<Buffer>>
    fun getAllDecks(): Future<HttpResponse<Buffer>>
    fun getDeckByName(name: String): Future<HttpResponse<Buffer>>
    fun appendCardToDeck(name: String, card: BaseCard): Future<HttpResponse<Buffer>>
    fun disconnect()
}