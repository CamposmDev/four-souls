package org.camposmdev.client.entity.component.card;

import javafx.scene.input.MouseEvent;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.util.Log;

public class LootCardComponent extends CardComponent {
	public LootCardComponent(LootCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
		texture().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			Log.info("clicked");
		});
	}

	@Override
	public LootCard card() {
		return (LootCard) super.card();
	}
}
