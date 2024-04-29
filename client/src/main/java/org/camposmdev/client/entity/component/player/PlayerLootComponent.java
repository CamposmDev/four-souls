package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.camposmdev.client.entity.component.card.LootCardComponent;
import org.camposmdev.client.model.Game;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

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
		Game game = FXGL.geto("game");
		var card = game.deck().loot().draw();
		if (card == null) {
			getNotificationService().pushNotification("Shuffle Loot Deck");
			return;
		}
		var data = new SpawnData().put("loot", card);
		var entity = getGameWorld().spawn("loot", data);
		entity.getComponentOptional(LootCardComponent.class).ifPresent(comp -> {
			inventory.add(comp);
			play("feedback/book page turn.wav");
		});
	}
}
