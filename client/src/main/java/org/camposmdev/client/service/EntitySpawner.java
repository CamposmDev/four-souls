package org.camposmdev.client.service;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import org.camposmdev.client.entity.TextureButtonEntity;
import org.camposmdev.client.entity.EntityType;
import org.camposmdev.client.entity.component.player.PlayerLootComponent;
import org.camposmdev.client.model.LocalGameManager;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.util.Log;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

public class EntitySpawner {
	public TextureButtonEntity monster_deck() {
		var entity = (TextureButtonEntity) getGameWorld().spawn("monster_deck");
		entity.setOnClick(() -> {
			Log.info("spawn monster");
		});
		return entity;
	}

	public TextureButtonEntity treasure_deck() {
		var entity = (TextureButtonEntity) getGameWorld().spawn("treasure_deck");
		entity.setOnClick(() -> {
			Log.info("draw top of treasure deck and return it");
		});
		return entity;
	}

	public TextureButtonEntity loot_deck() {
		var entity = (TextureButtonEntity) getGameWorld().spawn("loot_deck");
		entity.setOnClick(() -> {
			var player = getGameWorld().getSingleton(EntityType.PLAYER);
			player.getComponentOptional(PlayerLootComponent.class)
					.ifPresent(PlayerLootComponent::draw);
		});
		return entity;
	}

	public Entity player(String characterId) {
		LocalGameManager game = geto("game");
		CharacterCard character = game.deck().characters().find(x -> x.getId().equals(characterId));
		var data = new SpawnData().put("character", character);
		return getGameWorld().spawn("player", data);
	}

	public Entity monster() {
		LocalGameManager game = geto("game");
		BaseMonsterCard monster = game.deck().monsters().draw();
		/* check if the card is null, if it is, then shuffle
		 * the discard pile and set that as the new deck */
		var data = new SpawnData().put("card", monster);
		return getGameWorld().spawn("monster", data);
	}

	public Entity treasure() {
		LocalGameManager game = geto("game");
		TreasureCard treasure = game.deck().treasures().draw();
		/* check if the card is null, if it is, then shuffle
		 * the discard pile and set that as the new deck */
		var data = new SpawnData().put("card", treasure);
		return getGameWorld().spawn("treasure", data);
	}

	public Entity loot() {
		LocalGameManager game = geto("game");
		LootCard loot = game.deck().loot().draw();
		/* check if the cad is null, if it is, then shuffle
		 * the discard pile and set that as the new deck */
		var data = new SpawnData().put("card", loot);
		return getGameWorld().spawn("loot", data);
	}

	public Entity room() {
		LocalGameManager game = geto("game");
		RoomCard monster = game.deck().rooms().draw();
		/* check if the card is null, if it is, then shuffle
		 * the discard pile and set that as the new deck */
		var data = new SpawnData().put("card", monster);
		return getGameWorld().spawn("room", data);
	}
}
