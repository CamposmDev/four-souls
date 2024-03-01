package org.camposmdev.server.model;

import java.util.UUID;

public class Player implements Comparable<Player> {
    private final UUID id;
    private String username;
    private String password;

    public Player(String name, String password) {
        this.id = UUID.randomUUID();
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Player o) {
        return id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + username + '\'' +
//                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id.toString();
    }
}
