package org.camposmdev.server.middleware;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.RoutingContext;

public class Auth {
    private final JWTAuth provider;
    private Auth() {
        var vertx = Vertx.vertx();
        JWTAuthOptions config = new JWTAuthOptions();
        this.provider = JWTAuth.create(vertx, config);
    }

    private static final Auth auth = new Auth();

    public static Auth get() {
        return auth;
    }

    public static String generateToken(JsonObject jsonObject) {
        return get().provider.generateToken(jsonObject);
    }

    public static Handler<RoutingContext> verifyToken = c -> {
        var cookie = c.request().getCookie("token");
        if (cookie == null) {
            c.response().setStatusCode(401).send(JsonObject.of("message", "Missing JWT token").toString());
            return;
        }
        get().provider.authenticate(new TokenCredentials(cookie.getValue())).onSuccess(x -> c.next())
                .onFailure(x -> c.response().setStatusCode(401)
                        .send(JsonObject.of("message", "Unauthorized").toString()));
    };
}
