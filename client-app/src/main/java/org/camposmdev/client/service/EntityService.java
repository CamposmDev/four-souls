package org.camposmdev.client.service;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.dsl.FXGL;

/**
 * Service tool that currently can add events to entities. Manipulate the positions of entities. And
 * spawn in new entities.
 */
public class EntityService extends EngineService {
    private EntityEvents events;
    private EntityMapper mapper;
    private EntitySpawner spawn;

    @Override
    public void onInit() {
        events = new EntityEvents();
        mapper = new EntityMapper();
        spawn = new EntitySpawner();
    }

    public EntityEvents events() {
        return events;
    }

    public EntityMapper mapper() {
        return mapper;
    }

    public EntitySpawner spawn() {
        return spawn;
    }

    public static EntityService get() {
        return FXGL.getService(EntityService.class);
    }


}
