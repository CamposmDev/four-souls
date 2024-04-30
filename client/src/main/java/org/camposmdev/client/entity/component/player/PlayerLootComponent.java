package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.camposmdev.client.entity.component.card.LootCardComponent;
import org.camposmdev.client.service.EntityService;

import static com.almasb.fxgl.dsl.FXGLForKtKt.play;

public class PlayerLootComponent extends Component {
	private ObservableList<LootCardComponent> inventory;

	@Override
	public void onAdded() {
		inventory = FXCollections.observableArrayList();
	}

	public ObservableList<LootCardComponent> inventory() {
		return inventory;
	}

	public void addListener(ListChangeListener<LootCardComponent> change) {
		inventory.addListener(change);
	}

	public void draw() {
		var es = EntityService.get();
		var entity = es.spawn_loot();
		entity.getComponentOptional(LootCardComponent.class).ifPresent(comp -> {
			inventory.add(comp);
			play("feedback/book page turn.wav");
		});
	}
}
