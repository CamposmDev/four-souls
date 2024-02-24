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

    public UserRouter(Vertx vertx) {
        router = Router.router(vertx);
        router.post("/user/login").handler(this::login);
        router.get("/user").handler(Auth.verifyToken).handler(this::getOnlineUsers);
    }

    public Router get() {
        return router;
    }



    private void login(RoutingContext c) {
        var payload = c.body().asJsonObject();
        if (payload == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing body").toString());
            return;
        }
        var username = payload.getString("username");
        var password = payload.getString("password");
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
        if (ClientRegistry.getInstance().isPlayerTaken(username)) {
            c.response().setStatusCode(400).send(JsonObject.of("message", String.format("'%s' is already taken", username)).toString());
            return;
        }
        if (PlayerRegistry.getInstance().contains(username)) {
            /* check if the password matches */
            if (PlayerRegistry.getInstance().get(username).getPassword().equals(password)) {
                var p = PlayerRegistry.getInstance().get(username);
                var token = Auth.generateToken(JsonObject.of("userId", p.getId()));
                c.response()
                        .setStatusCode(200)
                        .addCookie(Cookie.cookie("token", token))
                        .send(JsonObject.of("userId", p.getId()).put("username", p.getName()).toString());
            } else { /* Password does not match */
                c.response().setStatusCode(400).send(JsonObject.of("message", "Password does not match").toString());
            }
        } else {
            /* create a new player */
            Player p = new Player(username, password);
            PlayerRegistry.getInstance().add(p);
            var token = Auth.generateToken(JsonObject.of("userId", p.getId()));
            c.response()
                    .setStatusCode(200)
                    .addCookie(Cookie.cookie("token", token))
                    .send(JsonObject.of("userId", p.getId()).put("username", p.getName()).toString());

        }
    }

    private void getOnlineUsers(RoutingContext c) {
        c.response()
                .setStatusCode(200)
                .send(JsonObject.of("online", ClientRegistry.getInstance().size()).toString());
    }
}
