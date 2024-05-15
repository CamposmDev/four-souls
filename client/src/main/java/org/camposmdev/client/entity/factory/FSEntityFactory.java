package org.camposmdev.client.entity.factory;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import org.camposmdev.client.entity.TextureButtonEntity;
import org.camposmdev.client.entity.EntityType;
import org.camposmdev.client.entity.component.*;
import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.entity.component.card.LootCardComponent;
import org.camposmdev.client.entity.component.card.MonsterCardComponent;
import org.camposmdev.client.entity.component.card.TreasureCardComponent;
import org.camposmdev.client.entity.component.player.*;
import org.camposmdev.client.model.LocalGameManager;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

public class FSEntityFactory implements EntityFactory {
    @Spawns("d6")
    public Entity d6(SpawnData data) {
        var entity = entityBuilder(data).with(new D6Component()).build();
        entity.getViewComponent().addOnClickHandler(event -> {
            entity.getComponent(D6Component.class).roll().onSuccess(x -> {

            });
        });
        return entity;
    }

    /**
     * Spawns in the back of the loot card
     * @param data
     * @return
     */
    @Spawns("loot_deck")
    public Entity loot_back(SpawnData data) {
		return new TextureButtonEntity("cards/LootCardBack.png");
    }

    /**
     * Spawns in the back of the treasure card
     * @param data
     * @return
     */
    @Spawns("treasure_deck")
    public Entity treasure_back(SpawnData data) {
		return new TextureButtonEntity("cards/TreasureCardBack.png");
    }

    /**
     * Spawns in the back of the monster card
     * @param data
     * @return
     */
    @Spawns("monster_deck")
    public Entity monster_back(SpawnData data) {
		return new TextureButtonEntity("cards/MonsterCardBack.png");
    }

    @Spawns("player")
    public Entity player(SpawnData data) {
        CharacterCard character = data.get("character");
        var game = (LocalGameManager) geto("game");
        var eternal = game.deck().eternals().find(x -> x.getId().equals(character.getEternalId()));
        if (eternal == null)
            throw new IllegalArgumentException("character's eternalId value cannot be found in eternal deck");
        return entityBuilder()
                .type(EntityType.PLAYER)
                .with(new HPComponent())
                .with(new ATKComponent())
                .with(new MoneyComponent())
                .with(new ScoreComponent())
                .with(new CharacterComponent(character))
                .with(new EternalItemComponent(eternal))
                .with(new PlayerLootComponent())
                .with(new PlayerItemsComponent())
                .with(new PlayerHUDComponent())
                .build();
    }

    @Spawns("card")
    public Entity card(SpawnData data) {
        BaseCard card = data.get("card");
        return entityBuilder(data)
                .with(new CardComponent(card))
                .build();
    }

    @Spawns("treasure")
    public Entity treasure(SpawnData data) {
        TreasureCard card = data.get("card");
        return entityBuilder(data)
                .with(new TreasureCardComponent(card))
                .build();
    }

    @Spawns("monster")
    public Entity monster(SpawnData data) {
        BaseMonsterCard card = data.get("card");
        return entityBuilder(data)
                .with(new HPComponent())
                .with(new DCComponent())
                .with(new ATKComponent())
                .with(new MonsterCardComponent(card))
                .build();
    }

    @Spawns("loot")
    public Entity loot(SpawnData data) {
        LootCard card = data.get("card");
        return entityBuilder(data)
                .with(new LootCardComponent(card))
                .build();
    }

    @Spawns("room")
    public Entity room(SpawnData data) {
        RoomCard card = data.get("card");
        /* TODO - Implement RoomCard entity */
        throw new RuntimeException("Implement me!");
    }
}
