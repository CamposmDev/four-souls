package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.camposmdev.client.entity.component.card.LootCardComponent;
import org.camposmdev.model.card.loot.LootCard;

public class PlayerLootComponent extends Component {
	private ObservableList<LootCardComponent> loot;

	@Override
	public void onAdded() {
		loot = FXCollections.observableArrayList();
	}

	public ObservableList<LootCardComponent> loot() {
		return loot;
	}

	public void addListener(ListChangeListener<LootCardComponent> change) {
		loot.addListener(change);
	}

	public void add(LootCard card) {
		var data = new SpawnData().put("loot", card);
		var entity = FXGL.getGameWorld().spawn("loot", data);
		entity.getComponentOptional(LootCardComponent.class).ifPresent(comp -> {
			loot.add(comp);
		});
	}
}
