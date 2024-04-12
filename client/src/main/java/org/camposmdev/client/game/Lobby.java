package org.camposmdev.client.game;

import io.vertx.core.json.JsonArray;

public class Lobby {
    private String id;
    private String hostId;
    private JsonArray players;

    public Lobby(String id, String hostId, JsonArray players) {
        this.id = id;
        this.hostId = hostId;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public Object getPlayers() {
//        return players;
        return null;
    }

    public void setPlayers(JsonArray players) {
        this.players = players;
    }

    public String[] getPlayerIds() {
        String[] arr = new String[players.size()];
        for (var i = 0; i < players.size(); i++) {
            var obj = players.getJsonObject(i);
            arr[i] = obj.getString("userId");
        }
        return arr;
    }

    public String[] getPlayerNames() {
        String[] arr = new String[players.size()];
        for (var i = 0; i < players.size(); i++) {
            var obj = players.getJsonObject(i);
            arr[i] = obj.getString("username");
        }
        return arr;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "id='" + id + '\'' +
                ", hostId='" + hostId + '\'' +
                ", players=" + players +
                '}';
    }
}