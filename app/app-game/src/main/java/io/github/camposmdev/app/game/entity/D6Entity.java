package io.github.camposmdev.app.game.entity;

import com.almasb.fxgl.entity.Entity;
import io.vertx.core.Future;
import io.github.camposmdev.app.game.entity.component.D6Component;

public class D6Entity extends Entity {
	private final D6Component component;

	public D6Entity() {
		component = new D6Component();
		addComponent(component);
	}

	public Future<Integer> roll() {
		return component.roll();
	}
}
