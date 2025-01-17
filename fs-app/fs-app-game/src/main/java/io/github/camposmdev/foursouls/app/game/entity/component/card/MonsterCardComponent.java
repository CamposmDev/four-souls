package io.github.camposmdev.foursouls.app.game.entity.component.card;

import io.github.camposmdev.foursouls.app.game.service.EntityService;
import io.github.camposmdev.foursouls.core.card.monster.BaseMonsterCard;

public class MonsterCardComponent extends CardComponent {
	public MonsterCardComponent(BaseMonsterCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
		var es = EntityService.get();
		es.events().add_onMouseHover_Feedback(this);
	}

	@Override
	public BaseMonsterCard card() {
		return (BaseMonsterCard) card;
	}
}
