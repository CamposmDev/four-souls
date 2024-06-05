package io.github.camposmdev.foursouls.model.api.mom

import io.github.camposmdev.foursouls.model.api.request.*
import io.github.camposmdev.foursouls.model.card.BaseCard
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpHeaders
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions

class MomAPI(v: Vertx, host: String, port: Int) : MomHttpClient {
    private var wc: WebClient
    var jwt: String? = null

    init {
        val options = WebClientOptions()
            .setDefaultHost(host)
            .setDefaultPort(port)
            .setUserAgent(UA)
            .setUserAgentEnabled(true)
        wc = WebClient.create(v, options)
    }

    override fun registerUser(email: String, username: String, password: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = CreateUserReq(email, username, password)
        wc.post("/api/user/").sendJson(payload).onComplete {
            if (it.succeeded()) {
                jwt = it.result().cookies().find { x -> x.startsWith(TOKEN_FIELD) }
                promise.complete(it.result())
            } else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun loginUser(username: String, password: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = LoginUserReq(username, password)
        wc.post("/api/user/login").sendJson(payload).onComplete {
            if (it.succeeded()) {
                jwt = it.result().cookies().find { x -> x.startsWith(TOKEN_FIELD) }
                promise.complete(it.result())
            } else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun logoutUser(): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.post("/api/user/logout")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).send().onComplete {
                if (it.succeeded()) {
                    jwt = null
                    promise.complete(it.result())
                } else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun getUserById(id: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/user/$id").send().onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun addBasement(floor: String, level: Int): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = CreateBasementReq(floor, level)
        wc.post("/api/basement/")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).sendJson(payload).onComplete {
            if (it.succeeded())
                promise.complete()
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun hostBasement(): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.post("/api/basement/host")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).send().onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun joinBasement(basementId: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/basement/$basementId/join")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).send().onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun freeBasement(basementId: String, basementKey: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = FreeBasementReq(basementId, basementKey)
        wc.post("/api/basement/$basementId/free")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).sendJson(payload).onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun addChest(location: String, gate: Int): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = CreateChestReq(location, gate)
        wc.post("/api/chest/")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).sendJson(payload).onComplete {
                if (it.succeeded())
                    promise.complete()
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun hostChest(): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.post("/api/chest/host")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).send().onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun joinChest(chestId: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/chest/$chestId/join")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).send().onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun unlockChest(chestId: String, chestKey: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = UnlockChestReq(chestId, chestKey)
        wc.post("/api/chest/$chestId/free")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).sendJson(payload).onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun getAllDecks(pretty: Boolean): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/deck?pretty=$pretty").send().onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun getDeckByName(name: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/deck/$name").send().onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun appendDeck(name: String, card: BaseCard): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.post("/api/deck/$name")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt).sendJson(card).onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future()
    }

    override fun ping(): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc.get("/api/ping/").send().onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun close() {
        wc.close()
    }

    private companion object {
        private const val UA = "Four-Souls/1.0.0"
        private const val TOKEN_FIELD = "token"
    }
}
