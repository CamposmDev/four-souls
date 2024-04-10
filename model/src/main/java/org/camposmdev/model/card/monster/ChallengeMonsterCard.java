package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.attribute.monster.ChallengeType;
import org.camposmdev.model.card.attribute.monster.GameType;

public class ChallengeMonsterCard extends AbstractMonsterCard {
    private GameType gameType;
    private ChallengeType challenge;

    public GameType gameType() {
        return gameType;
    }

    public ChallengeMonsterCard setGameType(GameType gameType) {
        this.gameType = gameType;
        return this;
    }

    public ChallengeType challenge() {
        return challenge;
    }

    public ChallengeMonsterCard setChallenge(ChallengeType challenge) {
        this.challenge = challenge;
        return this;
    }
}
