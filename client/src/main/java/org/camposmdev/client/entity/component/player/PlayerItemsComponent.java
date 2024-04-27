package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.camposmdev.client.entity.component.card.TreasureCardComponent;
import org.camposmdev.model.card.treasure.TreasureCard;

public class PlayerItemsComponent extends Component {
	private ObservableList<TreasureCardComponent> items;

	@Override
	public void onAdded() {
		this.items = FXCollections.observableArrayList();
	}

	public ObservableList<TreasureCardComponent> items() {
		return items;
	}

	public void addListener(ListChangeListener<TreasureCardComponent> change) {
		items.addListener(change);
	}

	public void add(TreasureCard card) {
		var data = new SpawnData().put("treasure", card);
		var entity = FXGL.getGameWorld().spawn("treasure", data);
		entity.getComponentOptional(TreasureCardComponent.class).ifPresent(comp -> {
			items.add(comp);
		});
	}
}
