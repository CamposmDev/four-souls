package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.util.Log;

public class PlayerComponent extends Component {
    private CharacterCard characterCard;
    private EternalCard eternalCard;
    private LifeComponent life;

    public PlayerComponent() {
        Log.info("player component initialized");
    }

    public void setCharacter(CharacterCard characterCard, EternalCard eternalCard) {
        this.characterCard = characterCard;
        this.eternalCard = eternalCard;
    }
}
