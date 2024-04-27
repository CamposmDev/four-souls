package org.camposmdev.client.entity.component.card;

import org.camposmdev.model.card.treasure.TreasureCard;

public class TreasureCardComponent extends CardComponent {
	public TreasureCardComponent(TreasureCard card) {
		super(card);
	}

	@Override
	public TreasureCard card() {
		return (TreasureCard) super.card();
	}
}
