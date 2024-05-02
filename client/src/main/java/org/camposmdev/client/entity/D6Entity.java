package org.camposmdev.client.entity;

import com.almasb.fxgl.entity.Entity;
import io.vertx.core.Future;
import org.camposmdev.client.entity.component.D6Component;

public class D6Entity extends Entity {
	private D6Component component;

	public D6Entity() {
		component = new D6Component();
		addComponent(component);
	}

	public Future<Integer> roll() {
		return component.roll();
	}
}
