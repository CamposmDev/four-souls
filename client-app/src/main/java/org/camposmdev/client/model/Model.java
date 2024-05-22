package org.camposmdev.client.model;

import io.vertx.core.json.JsonObject;

import java.util.Arrays;
import java.util.HashSet;

public class Model {
    private final UserAccount user;
    private Lobby currentLobby;

    public Model() {
        this.user = new UserAccount();
        this.currentLobby = null;
    }

    public void setUser(JsonObject arg) {
        user.setId(arg.getString("userId"));
        user.setUsername(arg.getString("username"));
    }

    public UserAccount getUser() {
        return user;
    }

    /**
     * Check to see if the {@link Model#currentLobby} host is {@link Model#user}
     * @return true if host id matches {@link Model#user} id, otherwise false
     */
    public boolean isHost() {
        return currentLobby.getHostId().equals(user.getId());
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
            /* init @code{currentLobby} first time */
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

    private static Model singleton;
    public static Model instance() {
        if (singleton == null)
            singleton = new Model();
        return singleton;
    }
}
