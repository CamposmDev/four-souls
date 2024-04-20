package org.camposmdev.client.game;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import org.camposmdev.client.game.component.D6AnimationComponent;
import org.camposmdev.client.game.component.PlayerComponent;
import org.camposmdev.util.FXUtil;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class FSGameFactory implements EntityFactory {
    static final int INLINE_PADDING = 16; /* X */
    static final int BLOCK_PADDING = 16; /* Y */

    @Spawns("d6")
    public Entity newD6(SpawnData data) {
        return entityBuilder(data).with(new D6AnimationComponent()).build();
    }

    @Spawns("loot-back")
    public Entity loot_back(SpawnData data) {
        return entityBuilder(data)
                .build();
    }

    @Spawns("treasure-back")
    public Entity treasure_back(SpawnData data) {
        return entityBuilder(data)
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        System.out.println(data);
        var texture = FXUtil.loadCard("cards/character/b-isaac.png");
        return entityBuilder(data)
                .with(new PlayerComponent())
                .view(texture)
                .at(INLINE_PADDING, getAppHeight() - texture.getFitHeight() - BLOCK_PADDING)
                .build();
    }
}
