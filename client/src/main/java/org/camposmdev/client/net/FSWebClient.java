package org.camposmdev.client.net;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonObject;
import org.camposmdev.client.model.UserAccount;

class FSWebClient {
    private final HttpClient client;

    protected FSWebClient(Vertx vertx, final String HOST, final int PORT) {
        var options = new HttpClientOptions().setDefaultHost(HOST).setDefaultPort(PORT);
        this.client = vertx.createHttpClient(options);
    }

    public Future<String> login(String username, String password) {
        Promise<String> promise = Promise.promise();
        var payload = JsonObject.of("username", username).put("password", password);
        client.request(HttpMethod.POST, "/api/user/login")
                .onSuccess(req -> req.send(payload.toString()).onSuccess(res -> {
                    var code = res.statusCode();
                    res.body().onSuccess(data -> {
                        var obj = data.toJsonObject();
                        if (code == 200) {
                            /* save our cookie token */
                            var authCookie = res.cookies().get(0);
                            API.get().setAuthCookie(authCookie);
                            promise.complete();
                            UserAccount.get().setParams(obj);
                            /* connect to websocket server */
                            API.get().initWS();
                        } else {
                            promise.fail(obj.getString("message"));
                        }
                    });
                })).onFailure(e -> promise.fail("Failed to connect to server"));
        return promise.future();
    }

    public Future<Integer> getOnlineUserCount() {
        Promise<Integer> promise = Promise.promise();
        client.request(HttpMethod.GET, "/api/user").onSuccess(req -> {
            req.headers().add(HttpHeaders.COOKIE, API.get().getAuthCookie());
            req.send().onSuccess(res -> res.body().onSuccess(data -> {
                if (res.statusCode() == 200) {
                    promise.complete(data.toJsonObject().getInteger("online"));
                } else {
                    promise.fail(data.toJsonObject().getString("message"));
                }
            })).onFailure(e -> promise.fail("Failed to connect to server"));
        });
        return promise.future();
    }

    public Future<String> hostGame() {
        Promise<String> promise = Promise.promise();
        client.request(HttpMethod.GET, "/api/game/host").onSuccess(req -> {
            req.headers().add(HttpHeaders.COOKIE, API.get().getAuthCookie());
            req.send().onSuccess(res -> res.body().onSuccess(data -> {
                var obj = data.toJsonObject();
                if (res.statusCode() == 200) {
                    promise.complete(obj.getString("gameId"));
                } else {
                    promise.fail(obj.getString("message"));
                }
            }));
        }).onFailure(e -> promise.fail("Failed to connect to server"));
        return promise.future();
    }

    public Future<JsonObject> joinGame(String gameId) {
        Promise<JsonObject> promise = Promise.promise();
        var payload = JsonObject.of("gameId", gameId).put("userId", UserAccount.get().getId());
        client.request(HttpMethod.POST, "/api/game/join").onSuccess(req -> {
            req.headers().add(HttpHeaders.COOKIE, API.get().getAuthCookie());
            req.send(payload.toString()).onSuccess(res -> res.body().onSuccess(data -> {
               var obj = data.toJsonObject();
               if (res.statusCode() == 200) {
                   promise.complete(obj);
               }
            })).onFailure(e -> promise.fail("Failed to connect to server"));
        });
        return promise.future();
    }

    public Future<Void> leaveGame(String gameId) {
        Promise<Void> promise = Promise.promise();
        var payload = JsonObject.of("gameId", gameId).put("userId", UserAccount.get().getId());
        client.request(HttpMethod.POST, "/api/game/leave").onSuccess(req -> {
            req.headers().add(HttpHeaders.COOKIE, API.get().getAuthCookie());
            req.send(payload.toString()).onSuccess(res -> {
                if (res.statusCode() == 200) {
                    promise.complete();
                } else promise.fail("Failed to leave game");
            }).onFailure(e -> promise.fail("Failed to connect to server"));
        });
        return promise.future();
    }

    public void close() {
        client.close();
    }
}
