package org.camposmdev.server.model;

import java.util.UUID;

public class Player implements Comparable<Player> {
    private UUID id;
    private String name;
    private String password;

    public Player(String name, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id.toString();
    }
}
