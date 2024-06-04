package io.github.camposmdev.foursouls.app.game.model;

public class UserAccount {
    private String id;
    private String username;

    public UserAccount() {
        this.id = null;
        this.username = null;
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }
}
