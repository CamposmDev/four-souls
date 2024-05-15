package org.camposmdev.client.entity.component.card;

import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.monster.BaseMonsterCard;

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
