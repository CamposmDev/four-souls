package org.camposmdev.server.model;

import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.MType;

public class WSAgent {
    private final ServerWebSocket ws;
    private final String userId;

    public WSAgent(String userId, ServerWebSocket ws) {
        this.userId = userId;
        this.ws = ws;
        this.ws.accept();
        this.ws.textMessageHandler(this::handleTextMessage);
        this.ws.closeHandler(this::closeHandler);
    }

    public String getUserId() {
        return userId;
    }

    private void handleTextMessage(String msg) {
        try {
            System.out.println(msg);
            var obj = new JsonObject(msg);
            handleJSON(obj);
        } catch (DecodeException ex) {
            /* Failed to parse JSON */
            ws.writeTextMessage(JsonObject.of("message", "Invalid JSON").toString());
        }
    }

    private void handleJSON(JsonObject arg) {
        if (arg.containsKey(MType.G_CHAT.name())) {
            ClientRegistry.get().notifyAll(arg);
        }
        else if (arg.containsKey(MType.L_CHAT.name())) {
            sendLobbyMessage(arg.getJsonObject(MType.L_CHAT.name()));
        }
        else if (arg.containsKey(MType.HOST_GAME.name())) {
            hostGame();
        }
        else if (arg.containsKey(MType.JOIN_LOBBY.name())) {
            joinGame(arg.getJsonObject(MType.JOIN_LOBBY.name()));
        }
        else if (arg.containsKey(MType.LEAVE_LOBBY.name())) {
            leaveGame(arg.getJsonObject(MType.LEAVE_LOBBY.name()));
        }
    }

    private void sendLobbyMessage(JsonObject arg) {
        var game = GameRegistry.get().getById(arg.getString("gameId"));
        var userId = arg.getString("userId");
        var username = PlayerRegistry.get().getById(userId).getUsername();
        var text = arg.getString("message");
        var payload = JsonObject.of(
                "username", username,
                "message", text
        );
        var message = JsonObject.of(MType.L_CHAT.name(), payload);
        for (var x : game.players()) {
            ClientRegistry.get().sendMessageTo(x.getId(), message);
        }
    }

    private void hostGame() {
        var game = new Game();
        game.addPlayer(PlayerRegistry.get().getById(userId));
        GameRegistry.get().add(game);
        var payload = game.toJSON();
        var message = JsonObject.of(MType.HOST_GAME.name(), payload);
        ws.writeTextMessage(message.toString());
    }

    private void joinGame(JsonObject arg) {
        var gameId = arg.getString("gameId");
        var userId = arg.getString("userId");
        var game = GameRegistry.get().getById(gameId);
        if (game == null) {
            sendErrMessage("Game ID is invalid");
            return;
        }
        var player = PlayerRegistry.get().getById(userId);
        if (player == null) {
            sendErrMessage("User ID is invalid");
            return;
        }
        if (game.players().contains(player)) {
            sendErrMessage("Player is already in game");
            return;
        }
        game.addPlayer(player);
        var payload = game.toJSON();
        var message1 = JsonObject.of(MType.JOIN_LOBBY.name(), payload);
        var message2 = JsonObject.of(MType.UPDATE_LOBBY.name(), payload);
        /* notify other players that are in the lobby that a new player joined */
        for (var x : game.players()) {
            /* skip the player that just joined */
            if (x.getId().equals(userId)) continue;
            ClientRegistry.get().sendMessageTo(x.getId(), message2);
        }
        ws.writeTextMessage(message1.toString());
    }

    private void leaveGame(JsonObject arg) {
        var gameId = arg.getString("gameId");
        var userId = arg.getString("userId");
        var game = GameRegistry.get().getById(gameId);
        if (game == null) {
            sendErrMessage("Game ID is invalid");
            return;
        }
        var player = PlayerRegistry.get().getById(userId);
        if (player == null) {
            sendErrMessage("User ID is invalid");
            return;
        }
        if (!game.players().contains(player)) {
            sendErrMessage("Player is not in game");
            return;
        }
        game.removePlayer(player);
        var payload = game.toJSON();
        var leaveMessage = JsonObject.of(MType.LEAVE_LOBBY.name(), payload);
        var updateMessage = JsonObject.of(MType.UPDATE_LOBBY.name(), payload);
        if (game.getHostId().equals(userId)) {
            /* if the host is the one leaving, then delete the game and notify other players */
            var closedMessage = JsonObject.of(MType.LOBBY_CLOSED.name(), JsonObject.of("message", "The host ended the game session."));
            GameRegistry.get().remove(game);
            for (var x : game.players()) {
                if (x.getId().equals(userId)) continue;
                ClientRegistry.get().sendMessageTo(x.getId(), closedMessage);
            }
        } else {
            /* notify other players that are in the lobby that a player left */
            for (var x : game.players()) {
                /* skip the player that just joined */
                if (x.getId().equals(userId)) continue;
                ClientRegistry.get().sendMessageTo(x.getId(), updateMessage);
            }
        }
        ws.writeTextMessage(leaveMessage.toString());
    }

    private void sendErrMessage(String text) {
        ws.writeTextMessage(JsonObject.of(MType.ERROR.name(),
                JsonObject.of("message", text)).toString());
    }

    private void closeHandler(Void x) {
        ClientRegistry.get().remove(this);
    }

    public ServerWebSocket getWS() {
        return ws;
    }

    @Override
    public String toString() {
        return ws.remoteAddress().toString();
    }
}
