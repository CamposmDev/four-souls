package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.context.store.state.MomState
import io.github.camposmdev.foursouls.model.api.mom.MomAPI
import io.github.camposmdev.foursouls.model.api.response.CreateUserRes
import io.github.camposmdev.foursouls.model.api.response.GetUserByIdRes
import io.github.camposmdev.foursouls.model.api.response.LoginUserRes
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx

class MomStore(v: Vertx, hostName: String, port: Int) : IStore<MomState> {
    private var api: MomAPI = MomAPI(v, hostName, port)
    private val state = MomState()

    override fun state(): MomState {
        return state
    }

    fun getJwt(): String? {
        return api.jwt
    }

    fun registerUser(email: String, username: String, password: String): Future<CreateUserRes> {
        val promise = Promise.promise<CreateUserRes>()
        api.registerUser(email, username, password).onSuccess { res ->
            if (res.statusCode() == 201) {
                val body = res.body().toJsonObject().mapTo(CreateUserRes::class.java)
                state.userId = body.id
                state.userId = body.username
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }

    fun loginUser(username: String, password: String): Future<LoginUserRes> {
        val promise = Promise.promise<LoginUserRes>()
        api.loginUser(username, password).onSuccess { res ->
            if (res.statusCode() == 200) {
                val body = res.body().toJsonObject().mapTo(LoginUserRes::class.java)
                state.userId = body.id
                state.username = body.username
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

    fun getUserById(id: String): Future<GetUserByIdRes> {
        val promise = Promise.promise<GetUserByIdRes>()
        api.getUserById(id).onSuccess { res ->
            if (res.statusCode() == 200) {
                val body = res.body().toJsonObject().mapTo(GetUserByIdRes::class.java)
                promise.complete(body)
            } else promise.fail(res.body().toString())
        }
        return promise.future()
    }
}
