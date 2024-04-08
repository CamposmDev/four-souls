package org.camposmdev.model.card.monster;

import org.camposmdev.model.game.Reward;

public class MonsterCard extends AbstractMonsterCard {
    private byte hitPoints;
    private byte damage;
    private byte roll;
    private Reward reward;
    private StartEvent startEvent;
    private PassiveEvent passiveEvent;
    private AttackEvent attackEvent;
    private DamageEvent damageEvent;
    private DeathEvent deathEvent;
    private EndEvent endEvent;

}
