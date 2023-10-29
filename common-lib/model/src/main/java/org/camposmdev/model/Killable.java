package org.camposmdev.model;

public interface Killable {
    int getATK();
    void damage(Killable entity);
    void attack(Killable entity);
}
