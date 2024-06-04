package io.github.camposmdev.foursouls.app.game.entity.component.card;

import io.github.camposmdev.foursouls.model.card.treasure.TreasureCard;

public class TreasureCardComponent extends CardComponent {
	public TreasureCardComponent(TreasureCard card) {
		super(card);
	}

	@Override
	public TreasureCard card() {
		return (TreasureCard) super.card();
	}
}
