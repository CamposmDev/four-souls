package org.camposmdev.client.entity.factory;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import org.camposmdev.client.entity.component.PlayerComponent;
import org.camposmdev.client.entity.component.D6Component;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.util.FXUtil;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameBoardFactory implements EntityFactory {
    @Spawns("d6")
    public Entity d6(SpawnData data) {
        var entity = entityBuilder(data).with(new D6Component()).build();
        entity.getViewComponent().addOnClickHandler(event -> {
            entity.getComponent(D6Component.class).roll().onSuccess(x -> {

            });
        });
        return entity;
    }

    @Spawns("loot_back")
    public Entity loot_back(SpawnData data) {
        var texture = FXUtil.loadCard("cards/LootCardBack.png");
        return entityBuilder(data)
                .view(texture)
                .build();
    }

    @Spawns("treasure_back")
    public Entity treasure_back(SpawnData data) {
        var texture = FXUtil.loadCard("cards/TreasureCardBack.png");
        return entityBuilder(data)
                .view(texture)
                .build();
    }

    @Spawns("monster_back")
    public Entity monster_back(SpawnData data) {
        var texture = FXUtil.loadCard("cards/MonsterCardBack.png");
        return entityBuilder(data)
                .view(texture)
                .build();
    }

    @Spawns("player")
    public Entity player(SpawnData data) {
        var characterCard = (CharacterCard) data.get("character");
        if (characterCard == null)
            throw new RuntimeException("Missing character data field");
        return entityBuilder(data)
                .with(new PlayerComponent(characterCard))
                .build();
    }
}
