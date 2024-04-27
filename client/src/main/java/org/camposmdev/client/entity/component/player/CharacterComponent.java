package org.camposmdev.client.entity.component.player;

import org.camposmdev.client.entity.component.ATKComponent;
import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.entity.component.HPComponent;
import org.camposmdev.client.entity.component.PlayableComponent;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.character.CharacterCard;

public class CharacterComponent extends CardComponent implements PlayableComponent {
	public CharacterComponent(CharacterCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
		entity.getComponentOptional(HPComponent.class).ifPresent(comp -> {
			comp.max().set(this.card().getHp());
			comp.current().set(this.card().getHp());
		});
		entity.getComponentOptional(ATKComponent.class).ifPresent(comp -> {
			comp.max().set(this.card().getAtk());
			comp.current().set(this.card().getAtk());
		});
		texture().setOnMouseClicked(event -> {
			texture().setRotate(texture().getRotate() + 90);
		});
		EntityService.get().events().onMouseHover_Preview(this);
		EntityService.get().events().onMouseHover_Highlight(this);
	}

	@Override
	public CharacterCard card() {
		return (CharacterCard) super.card();
	}

	@Override
	public void play() {
		/* TODO - Implement character abilities */
	}
}
