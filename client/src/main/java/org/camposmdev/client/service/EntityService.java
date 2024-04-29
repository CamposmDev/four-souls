package org.camposmdev.client.service;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import org.camposmdev.client.model.Game;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

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

    public void spawnPlayer(String characterId) {
        var game = (Game) geto("game");
        var character = game.deck().characters().find(x -> x.getId().equals(characterId));
        if (character == null) throw new RuntimeException("character is null");
        var data = new SpawnData();
        data.put("character", character);
        getGameWorld().spawn("player", data);
    }

    public static EntityService get() {
        return FXGL.getService(EntityService.class);
    }
}
