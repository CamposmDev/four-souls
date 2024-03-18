package org.camposmdev.client.game;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.game.Lobby;

import java.util.Arrays;
import java.util.HashSet;

public class UserContext {
    private static UserContext singleton;
    public static UserContext get() {
        if (singleton == null)
            singleton = new UserContext();
        return singleton;
    }
    private String id;
    private String username;
    private Lobby currentLobby;

    public void setParams(JsonObject arg) {
        id = arg.getString("userId");
        username = arg.getString("username");
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isHosting() {
        return currentLobby.getHostId().equals(id);
    }

    /**
     * @param arg JSON object to be parsed to Lobby object
     * @return Returns the username of the player who left or joined
     */
    public String setCurrentLobby(JsonObject arg) {
        if (arg == null) {
            currentLobby = null;
            return null;
        } else if (currentLobby == null) {
            /* initialize currentLobby first time */
            var id = arg.getString("gameId");
            var hostId = arg.getString("hostId");
            var players = arg.getJsonArray("players");
            currentLobby = new Lobby(id, hostId, players);
            return null;
        } else {
            var id = arg.getString("gameId");
            var hostId = arg.getString("hostId");
            var players = arg.getJsonArray("players");
            var oldLobby = currentLobby;
            var newLobby = new Lobby(id, hostId, players);
            currentLobby = newLobby;
            /* find the player who joined/left */
            var names1 = oldLobby.getPlayerNames();
            var names2 = newLobby.getPlayerNames();
            if (names1.length < names2.length) {
                /* a player has joined  */
                var set = new HashSet<>(Arrays.asList(names1));
                var i = 0;
                for (i = 0; set.contains(names2[i]); i++);
                return String.format("%s joined the game\n", names2[i]);
            } else {
                /* a player has left */
                var set = new HashSet<>(Arrays.asList(names2));
                var i = 0;
                for (i = 0; set.contains(names1[i]); i++);
                return String.format("%s left the game\n", names1[i]);
            }
        }
    }

    public Lobby getCurrentLobby() {
        return currentLobby;
    }
}
