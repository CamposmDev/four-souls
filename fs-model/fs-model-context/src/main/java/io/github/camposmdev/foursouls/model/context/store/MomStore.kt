package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.api.mom.MomAPI
import io.github.camposmdev.foursouls.model.api.response.CreateUserRes
import io.github.camposmdev.foursouls.model.api.response.GetUserByIdRes
import io.github.camposmdev.foursouls.model.api.response.LoginUserRes
import io.github.camposmdev.foursouls.model.api.response.MessageRes
import io.github.camposmdev.foursouls.model.context.store.state.MomState
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
            val job = res.body().toJsonObject()
            if (res.statusCode() == 201) {
                val body = job.mapTo(CreateUserRes::class.java)
                state.userId = body.id
                state.userId = body.username
                promise.complete(body)
            } else {
                val body = job.mapTo(MessageRes::class.java)
                promise.fail(body.message)
            }
        }
        return promise.future()
    }

    fun loginUser(username: String, password: String): Future<LoginUserRes> {
        val promise = Promise.promise<LoginUserRes>()
        api.loginUser(username, password).onSuccess { res ->
            val job = res.body().toJsonObject()
            if (res.statusCode() == 200) {
                val body = job.mapTo(LoginUserRes::class.java)
                state.userId = body.id
                state.username = body.username
                promise.complete(body)
            } else {
                val body = job.mapTo(MessageRes::class.java)
                promise.fail(body.message)
            }
        }
        return promise.future()
    }

    fun logoutUser(): Future<String> {
        val promise = Promise.promise<String>()
        api.logoutUser().onSuccess { res ->
            val body = res.body().toJsonObject().mapTo(MessageRes::class.java)
            if (res.statusCode() == 200) {
                promise.complete(body.message)
            } else promise.fail(body.message)
        }
        return promise.future()
    }

    fun getUserById(id: String): Future<GetUserByIdRes> {
        val promise = Promise.promise<GetUserByIdRes>()
        api.getUserById(id).onSuccess { res ->
            val job = res.body().toJsonObject()
            if (res.statusCode() == 200) {
                val body = job.mapTo(GetUserByIdRes::class.java)
                promise.complete(body)
            } else {
                val body = job.mapTo(MessageRes::class.java)
                promise.fail(body.message)
            }
        }
        return promise.future()
    }
}
