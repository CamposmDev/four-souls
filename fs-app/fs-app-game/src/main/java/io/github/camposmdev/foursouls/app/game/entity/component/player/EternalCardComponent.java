package io.github.camposmdev.foursouls.app.game.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import io.github.camposmdev.foursouls.app.game.entity.component.Playable;
import io.github.camposmdev.foursouls.app.game.entity.component.card.CardRenderer;
import io.github.camposmdev.foursouls.app.game.service.EntityService;
import io.github.camposmdev.foursouls.core.card.eternal.EternalCard;

public class EternalCardComponent extends Component implements CardRenderer<EternalCard>, Playable {
	private PlayerComponent player; /* this component is injected automatically */
	private Texture texture;

	@Override
	public void onAdded() {
		/* TODO - refactor */
//		texture = FXUtil.loadCard(player.eternal().getImage().loResSrc());
		/* TODO - Add necessary components based on the type of eternal card */
		texture().setOnMouseClicked(event -> {
			texture().setRotate(texture().getRotate() + 90);
		});
		EntityService.get().events().add_onMouseHover_PreviewAndFeedBack(this);
	}

	@Override
	public EternalCard card() {
		return player.eternal();
	}

	@Override
	public void setCard(EternalCard card) {
		player.setEternal(card);
	}

	@Override
	public Texture texture() {
		return texture;
	}

	@Override
	public double width() {
		return texture.getFitWidth();
	}

	@Override
	public double height() {
		return texture.getFitHeight();
	}

	@Override
	public Texture hiResTexture() {
//		return FXUtil.loadCard(player.eternal().getImage().hiResSrc());
		return null;
	}

	@Override
	public Texture loResTexture() {
//		return FXUtil.loadCard(player.eternal().getImage().loResSrc());
		return null;
	}

	@Override
	public void play() {
		/* TODO - refactor code */
		switch (card().getCardType()) {
//			case AETERNAL -> playActiveCard((ActiveEternalCard) card());
//			case PAIDETERNAL -> playPaidCard((PaidEternalCard) card());
//			case PETERNAL -> playPassiveCard((PassiveEternalCard) card());
//			case SETERNAL -> playSoulCard((SoulEternalCard) card());
		}
	}
}
