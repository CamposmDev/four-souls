package org.camposmdev.client.entity.component.card;

import org.camposmdev.model.card.loot.LootCard;

public class LootCardComponent extends CardComponent {
	public LootCardComponent(LootCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
	}

	@Override
	public LootCard card() {
		return (LootCard) super.card();
	}
}
