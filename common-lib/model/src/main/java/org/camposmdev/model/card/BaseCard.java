package org.camposmdev.model.card;


import java.io.Serializable;
import java.util.UUID;

public abstract class BaseCard implements Serializable {
    private UUID id;
    protected String name;

    public BaseCard(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public BaseCard() {
        this.id = UUID.randomUUID();
    }

    public String getId() {
        return this.id.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
