package org.camposmdev.client.service;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.entity.Entity;

public class EntityService extends EngineService {
    private EntityAnimator animator;
    private EntityMapper mapper;

    @Override
    public void onInit() {
        animator = new EntityAnimator();
        mapper = new EntityMapper();
    }

    public EntityAnimator animate() {
        return animator;
    }

    public void map(Entity entity, BoardPosition position) {
        mapper.map(entity, position);
    }
}
