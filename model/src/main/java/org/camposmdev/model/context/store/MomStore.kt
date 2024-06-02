package org.camposmdev.model.context.store

import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import org.camposmdev.model.context.store.state.MomState
import org.camposmdev.model.net.api.MomApi
import org.camposmdev.model.net.response.CreateUserBodyRes
import org.camposmdev.model.net.response.GetUserByIdBodyRes
import org.camposmdev.model.net.response.LoginUserBodyRes

class MomStore(v: Vertx, hostName: String = DEFAULT_HOST, port: Int = DEFAULT_PORT) : IStore<MomState> {
    private companion object {
        private const val DEFAULT_HOST = "localhost"
        private const val DEFAULT_PORT = 8080
    }
    private var api: MomApi = MomApi(v, hostName, port)
    private val currState = MomState()

    override fun getState(): MomState {
        return currState
    }

    override fun setState(state: MomState) {
        this.currState.userId = state.userId?: currState.userId
        this.currState.username = state.username?: currState.username
    }

    fun getJwt(): String? {
        return api.jwt
    }

    fun registerUser(email: String, username: String, password: String): Future<CreateUserBodyRes> {
        val promise = Promise.promise<CreateUserBodyRes>()
        api.registerUser(email, username, password).onSuccess { res ->
            if (res.statusCode() == 201) {
                val body = res.body().toJsonObject().mapTo(CreateUserBodyRes::class.java)
                currState.userId = body.id
                currState.userId = body.username
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun loginUser(username: String, password: String): Future<LoginUserBodyRes> {
        val promise = Promise.promise<LoginUserBodyRes>()
        api.loginUser(username, password).onSuccess { res ->
            if (res.statusCode() == 200) {
                val body = res.body().toJsonObject().mapTo(LoginUserBodyRes::class.java)
                currState.userId = body.id
                currState.username = body.username
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun logoutUser(): Future<Void> {
        val promise = Promise.promise<Void>()
        api.logoutUser().onSuccess { res ->
            if (res.statusCode() == 200)
                promise.complete()
            else promise.fail(res.body().toString())
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
