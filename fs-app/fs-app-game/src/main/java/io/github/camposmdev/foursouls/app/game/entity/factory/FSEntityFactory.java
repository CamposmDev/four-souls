package io.github.camposmdev.foursouls.app.game.entity.factory;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import io.github.camposmdev.foursouls.app.game.entity.EntityType;
import io.github.camposmdev.foursouls.app.game.entity.TextureButtonEntity;
import io.github.camposmdev.foursouls.app.game.entity.component.ATKComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.D6Component;
import io.github.camposmdev.foursouls.app.game.entity.component.DCComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.HPComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.card.CardComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.card.LootCardComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.card.MonsterCardComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.card.TreasureCardComponent;
import io.github.camposmdev.foursouls.app.game.entity.component.player.*;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.loot.LootCard;
import io.github.camposmdev.foursouls.model.card.monster.BaseMonsterCard;
import io.github.camposmdev.foursouls.model.card.room.RoomCard;
import io.github.camposmdev.foursouls.model.card.treasure.TreasureCard;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

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
        final var CHARACTER_ID = "characterId";
        String characterId = data.get(CHARACTER_ID);
        return entityBuilder()
                .type(EntityType.PLAYER)
                .with(CHARACTER_ID, characterId)
                .with(new PlayerComponent())
                .with(new CharacterCardComponent())
                .with(new EternalCardComponent())
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
