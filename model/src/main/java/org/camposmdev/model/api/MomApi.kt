package org.camposmdev.model.api

import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpHeaders
import io.vertx.ext.web.client.HttpResponse
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import org.camposmdev.model.card.BaseCard
import org.camposmdev.model.net.request.CreateUserBodyReq
import org.camposmdev.model.net.request.LoginUserBodyReq

class MomApi(v: Vertx, hostName: String, port: Int) : MomHttpClient {
    private val UA = "Four-Souls/1.0.0"
    private var wc: WebClient? = null

    init {
        val options = WebClientOptions()
            .setDefaultHost(hostName)
            .setDefaultPort(port)
            .setUserAgent(UA)
        wc = WebClient.create(v, options)
    }

    override fun registerUser(email: String, username: String, password: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = CreateUserBodyReq().apply {
            this.email = email
            this.username = username
            this.password = password
        }
        wc!!.post("/api/user/").sendJson(payload).onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun loginUser(username: String, password: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        val payload = LoginUserBodyReq().apply {
            this.username = username
            this.password = password
        }
        wc!!.post("/api/user/login").sendJson(payload).onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun logoutUser(jwt: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc!!.post("/api/user/logout")
            .putHeader(HttpHeaders.COOKIE.toString(), jwt)
            .send().onComplete {
                if (it.succeeded())
                    promise.complete(it.result())
                else promise.fail(it.cause())
            }
        return promise.future();
    }

    override fun getUserById(id: String): Future<HttpResponse<Buffer>> {
        val promise = Promise.promise<HttpResponse<Buffer>>()
        wc!!.get("/api/user/$id").send().onComplete {
            if (it.succeeded())
                promise.complete(it.result())
            else promise.fail(it.cause())
        }
        return promise.future()
    }

    override fun hostBasement(): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun joinBasement(basementId: String): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun freeBasement(basementId: String, basementKey: String): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun hostChest(): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun joinChest(chestId: String): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun freeChest(chestId: String, chestKey: String): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun getAllDecks(): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun getDeckByName(name: String): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun appendCardToDeck(name: String, card: BaseCard): Future<HttpResponse<Buffer>> {
        /* TODO - Implement end point */
        val promise = Promise.promise<HttpResponse<Buffer>>()
        return promise.future()
    }

    override fun disconnect() {
        wc!!.close()
    }
}
