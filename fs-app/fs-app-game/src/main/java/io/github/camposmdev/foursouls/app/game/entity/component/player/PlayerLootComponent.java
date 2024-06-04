package io.github.camposmdev.foursouls.app.game.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import io.github.camposmdev.foursouls.app.game.entity.component.card.LootCardComponent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import io.github.camposmdev.foursouls.app.game.service.EntityService;

import static com.almasb.fxgl.dsl.FXGLForKtKt.play;

public class PlayerLootComponent extends Component {
	private PlayerComponent player;
	private ObservableList<LootCardComponent> inventory;

	@Override
	public void onAdded() {
		inventory = FXCollections.observableArrayList();
		addListener(change -> {
			while (change.next()) {
				if (change.wasAdded()) {
					play("feedback/book page turn.wav");
				}
			}
		});
	}

	public ObservableList<LootCardComponent> inventory() {
		return inventory;
	}

	public void addListener(ListChangeListener<LootCardComponent> change) {
		inventory.addListener(change);
	}

	public void draw() {
		var entity = EntityService.get().spawn().loot();
		entity.getComponentOptional(LootCardComponent.class).ifPresent(comp -> {
			inventory.add(comp);
		});
	}
}
