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
        var username = payload.getString("username");
        var password = payload.getString("password");
        if (username.isBlank()) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Invalid Username").toString());
            return;
        }
        if (password.isBlank()) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Password can't be empty").toString());
            return;
        }
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
                String token = Auth.generateToken(JsonObject.of("username", username));
                c.response()
                        .setStatusCode(200)
                        .addCookie(Cookie.cookie("token", token))
                        .send(JsonObject.of("message", "Login successful!").toString());
            }
        } else {
            /* create a new player */
            Player p = new Player(username, password);
            PlayerRegistry.getInstance().add(p);
            String token = Auth.generateToken(JsonObject.of("username", username));
            c.response()
                    .setStatusCode(200)
                    .addCookie(Cookie.cookie("token", token))
                    .send(JsonObject.of("message", "Login successful!").toString());
        }
    }

    private void getOnlineUsers(RoutingContext c) {
        c.response()
                .setStatusCode(200)
                .send(JsonObject.of("online", ClientRegistry.getInstance().size()).toString());
    }
}
