package org.camposmdev.model.card.loot;

import org.camposmdev.model.Reward;
import org.camposmdev.model.card.BaseCard;

public class LootCard extends BaseCard {
    private Reward reward;

    public LootCard(Reward reward) {
        this.reward = reward;
    }

    public Reward getReward() {
        return reward;
    }
}
