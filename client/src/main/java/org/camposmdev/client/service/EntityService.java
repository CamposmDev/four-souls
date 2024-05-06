package org.camposmdev.client.service;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import org.camposmdev.client.model.Game;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

/**
 * Service tool for manipulating and creating entities
 */
public class EntityService extends EngineService {
    private EntityEvents events;
    private EntityMapper mapper;

    @Override
    public void onInit() {
        events = new EntityEvents();
        mapper = new EntityMapper();
    }

    public EntityEvents events() {
        return events;
    }

    public EntityMapper mapper() {
        return mapper;
    }

    public void spawn_player(String characterId) {
        Game game = geto("game");
        CharacterCard character = game.deck().characters().find(x -> x.getId().equals(characterId));
        var data = new SpawnData().put("character", character);
        getGameWorld().spawn("player", data);
    }

    public Entity spawn_treasure() {
        Game game = geto("game");
        TreasureCard treasure = game.deck().treasures().draw();
        /* check if the card is null, if it is, then shuffle
         * the discard pile and set that as the new deck */
        var data = new SpawnData().put("card", treasure);
        return getGameWorld().spawn("treasure", data);
    }

    public Entity spawn_monster() {
        Game game = geto("game");
        BaseMonsterCard monster = game.deck().monsters().draw();
        /* check if the card is null, if it is, then shuffle
        * the discard pile and set that as the new deck */
        var data = new SpawnData().put("card", monster);
        return getGameWorld().spawn("monster", data);
    }

    public Entity spawn_loot() {
        Game game = geto("game");
        LootCard loot = game.deck().loot().draw();
        /* check if the cad is null, if it is, then shuffle
        * the discard pile and set that as the new deck */
        var data = new SpawnData().put("card", loot);
        return getGameWorld().spawn("loot", data);
    }

    public Entity spawn_room() {
        Game game = geto("game");
        RoomCard monster = game.deck().rooms().draw();
        /* check if the card is null, if it is, then shuffle
        * the discard pile and set that as the new deck */
        var data = new SpawnData().put("card", monster);
        return getGameWorld().spawn("room", data);
    }

    public static EntityService get() {
        return FXGL.getService(EntityService.class);
    }
}
