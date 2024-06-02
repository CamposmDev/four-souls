package org.camposmdev.model.store

import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import org.camposmdev.model.api.MomApi
import org.camposmdev.model.net.response.CreateUserBodyRes
import org.camposmdev.model.net.response.GetUserByIdBodyRes
import org.camposmdev.model.net.response.LoginUserBodyRes

private const val DEFAULT_HOST_NAME = "localhost"
private const val DEFAULT_PORT = 8080
private const val TOKEN_FIELD = "token"


class MomStore(v: Vertx, hostName: String = DEFAULT_HOST_NAME, port: Int = DEFAULT_PORT) {
    private var api: MomApi = MomApi(v, hostName, port)
    var jwt: String? = null
    var userId: String? = null
    var username: String? = null

    fun registerUser(email: String, username: String, password: String): Future<CreateUserBodyRes> {
        val promise = Promise.promise<CreateUserBodyRes>()
        api.registerUser(email, username, password).onSuccess { res ->
            if (res.statusCode() == 201) {
                jwt = res.cookies().find { x -> x.startsWith(TOKEN_FIELD) }
                val body = res.body().toJsonObject().mapTo(CreateUserBodyRes::class.java)
                this.userId = body.id
                this.username = body.username
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun loginUser(username: String, password: String): Future<LoginUserBodyRes> {
        val promise = Promise.promise<LoginUserBodyRes>()
        api.loginUser(username, password).onSuccess { res ->
            if (res.statusCode() == 200) {
                jwt = res.cookies().find { x -> x.startsWith(TOKEN_FIELD)}
                val body = res.body().toJsonObject().mapTo(LoginUserBodyRes::class.java)
                this.userId = body.id
                this.username = body.username
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun logoutUser(): Future<Void> {
        val promise = Promise.promise<Void>()
        api.logoutUser(jwt!!).onSuccess { res ->
            if (res.statusCode() == 200) {
                jwt = null
                promise.complete()
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun getUserById(id: String): Future<GetUserByIdBodyRes> {
        val promise = Promise.promise<GetUserByIdBodyRes>()
        api.getUserById(id).onSuccess { res ->
            if (res.statusCode() == 200) {
                val body = res.body().toJsonObject().mapTo(GetUserByIdBodyRes::class.java)
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }
}
