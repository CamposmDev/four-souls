package org.camposmdev.client.entity.component.player;

import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.entity.component.PlayableComponent;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.eternal.*;

public class EternalItemComponent extends CardComponent implements PlayableComponent {
	public EternalItemComponent(EternalCard card) {
		super(card);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EternalCard card() {
		return (EternalCard) super.card();
	}

	@Override
	public void onAdded() {
		super.onAdded();
		/* TODO - Add necessary components based on the type of eternal card */
		texture().setOnMouseClicked(event -> {
			texture().setRotate(texture().getRotate() + 90);
		});
		EntityService.get().events().onMouseHover_Preview(this);
		EntityService.get().events().onMouseHover_Highlight(this);
	}

	@Override
	public void play() {
		switch (card().getCardType()) {
			case AETERNAL -> playActiveCard((ActiveEternalCard) card());
			case PAIDETERNAL -> playPaidCard((PaidEternalCard) card());
			case PETERNAL -> playPassiveCard((PassiveEternalCard) card());
			case SETERNAL -> playSoulCard((SoulEternalCard) card());
		}
	}

	private void playActiveCard(ActiveEternalCard card) {
		switch (card.getItem()) {
			case THE_D6 -> {
				/* TODO - Implement reroll effect
				* Take the most previous roll that was done by a player. Undo that roll and
				* force the player to reroll */
			}
			default -> throw new IllegalArgumentException("Hmmm... that type of item shouldn't be there...");
		}
	}

	private void playPaidCard(PaidEternalCard card) {

	}

	private void playPassiveCard(PassiveEternalCard card) {

	}

	private void playSoulCard(SoulEternalCard card) {

	}
}
