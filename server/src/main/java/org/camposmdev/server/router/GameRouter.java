package org.camposmdev.server.router;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.camposmdev.server.middleware.Auth;
import org.camposmdev.server.model.Game;
import org.camposmdev.server.model.GameRegistry;
import org.camposmdev.server.model.PlayerRegistry;

public class GameRouter {
    private final Router router;

    public static Router init(Vertx vertx) {
        return new GameRouter(vertx).router;
    }

    private GameRouter(Vertx vertx) {
        router = Router.router(vertx);
        router.get("/game/host").handler(Auth.verifyToken).handler(this::hostGame);
        router.post("/game/join").handler(Auth.verifyToken).handler(this::joinGame);
        router.post("/game/leave").handler(Auth.verifyToken).handler(this::leaveGame);
    }

    private void hostGame(RoutingContext c) {
        var game = new Game();
        GameRegistry.get().add(game);
        var payload = JsonObject.of("gameId", game.getId());
        c.response().setStatusCode(200).send(payload.toString());
    }

    private void joinGame(RoutingContext c) {
        var obj = c.body().asJsonObject();
        if (obj == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing body").toString());
            return;
        }
        var gameId = obj.getString("gameId");
        var userId = obj.getString("userId");
        if (gameId == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing gameId field").toString());
            return;
        }
        if (userId == null) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Missing userId field").toString());
            return;
        }
        /* check if the game does not exist in the registry */
        if (!GameRegistry.get().contains(gameId)) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Invalid gameId").toString());
            return;
        }
        /* check if the player does not exist in the registry */
        if (!PlayerRegistry.get().containsId(userId)) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "Invalid userId").toString());
            return;
        }
        /* check if the player is already in the game */
        var p = PlayerRegistry.get().getById(userId);
        var g = GameRegistry.get().get(gameId);
        if (g.players().contains(p)) {
            c.response().setStatusCode(400).send(JsonObject.of("message", "You already joined this game").toString());
            return;
        }
        /* otherwise add the player to the game */
        g.addPlayer(p);
        var arr = new JsonArray();
        for (var x : g.players()) {
            arr.add(JsonObject.of("userId", x.getId()).put("username", x.getUsername()));
        }
        var payload = JsonObject.of("gameId", gameId)
                        .put("players", arr);
        c.response().setStatusCode(200).end(payload.toString());
    }

    private void leaveGame(RoutingContext c) {

    }
}
