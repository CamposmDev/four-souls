package org.camposmdev.client.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import org.camposmdev.client.model.Game;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.Log;

public class PlayerComponent extends Component {
    private CharacterCard characterCard;
    private EternalCard eternalCard;
    private HPComponent life;

    public PlayerComponent(CharacterCard characterCard) {
        Log.info("init player component");
        this.characterCard = characterCard;
        Log.info("player is " + characterCard.getId());
        var game = (Game) FXGL.geto("game");
        this.eternalCard = game.deck().eternals().get(x -> x.getId().equals(characterCard.getEternalId()));
        Log.info("eternal is " + eternalCard.getId());
    }

    @Override
    public void onAdded() {
        /* display character card image */
        var iv = FXUtil.loadCard(characterCard.getImage().source1());
        getEntity().getViewComponent().addChild(iv);
        Log.infof("Added view %s", iv.getImage().getUrl());
    }
}
