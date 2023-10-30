package org.camposmdev.model;

import org.camposmdev.model.card.statistic.Attribute;

public interface Killable {
    Attribute getATK();
    void damage(Killable entity);
    void attack(Killable entity);
    boolean isDead();
}
