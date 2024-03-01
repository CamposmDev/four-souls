package org.camposmdev.client.model;

import io.vertx.core.json.JsonObject;

public class UserAccount {
    private static UserAccount singleton;
    public static UserAccount get() {
        if (singleton == null)
            singleton = new UserAccount();
        return singleton;
    }
    private String id;
    private String username;

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
}
