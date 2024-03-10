package org.camposmdev.client.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public class GameFactory implements EntityFactory {
    @Spawns("d6")
    public Entity newD6(SpawnData data) {
        return FXGL.entityBuilder(data).with(new D6AnimationComponent()).build();
    }
}
