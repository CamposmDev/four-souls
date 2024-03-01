package org.camposmdev.server.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.Cookie;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.camposmdev.server.middleware.Auth;
import org.camposmdev.server.model.ClientRegistry;
import org.camposmdev.server.model.Player;
import org.camposmdev.server.model.PlayerRegistry;

public class UserRouter {
    private final Router router;

    public static Router init(Vertx vertx) {
        return new UserRouter(vertx).router;
    }

    private UserRouter(Vertx vertx) {
        router = Router.router(vertx);
        router.post("/user/login").handler(this::login);
        router.get("/user").handler(Auth.verifyToken).handler(this::getOnlineUsers);
    }

    private void login(RoutingContext c) {
        var obj = c.body().asJsonObject();
        if (obj == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing body").toString());
            return;
        }
        var username = obj.getString("username");
        var password = obj.getString("password");
        if (username == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing username field").toString());
            return;
        }
        if (password == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing password field").toString());
            return;
        }
        if (username.isBlank()) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Invalid Username").toString());
            return;
        }
        if (password.isBlank()) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Invalid Password").toString());
            return;
        }
        /* check if password is at least {PWD_LEN} characters */
        final var PWD_LEN = 7;
        if (password.length() < PWD_LEN) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Password must at least " + PWD_LEN + " characters").toString());
            return;
        }
//        if (ClientRegistry.getInstance().isPlayerTaken(username)) {
//            c.response().setStatusCode(400).send(JsonObject.of("message", String.format("'%s' is already taken", username)).toString());
//            return;
//        }
        /* check if a player with username already exists */
        if (PlayerRegistry.get().containsUsername(username)) {
            /* check if the password matches */
            if (PlayerRegistry.get().getByUsername(username).getPassword().equals(password)) {
                var p = PlayerRegistry.get().getByUsername(username);
                var token = Auth.generateToken(JsonObject.of("userId", p.getId()));
                c.response()
                        .setStatusCode(200)
                        .addCookie(Cookie.cookie("token", token))
                        .send(JsonObject.of("userId", p.getId()).put("username", p.getUsername()).toString());
            } else { /* Password does not match */
                c.response().setStatusCode(400).send(JsonObject.of("message", "Password does not match").toString());
            }
        } else {
            /* otherwise create a new player */
            Player p = new Player(username, password);
            PlayerRegistry.get().add(p);
            var token = Auth.generateToken(JsonObject.of("userId", p.getId()));
            c.response()
                    .setStatusCode(200)
                    .addCookie(Cookie.cookie("token", token).setPath("/"))
                    .send(JsonObject.of("userId", p.getId()).put("username", p.getUsername()).toString());

        }
    }

    private void getOnlineUsers(RoutingContext c) {
        c.response()
                .setStatusCode(200)
                .send(JsonObject.of("online", ClientRegistry.get().size()).toString());
    }
}
