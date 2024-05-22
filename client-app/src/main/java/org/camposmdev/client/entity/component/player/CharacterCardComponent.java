package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import org.camposmdev.client.entity.component.Playable;
import org.camposmdev.client.entity.component.card.CardRenderer;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.game.Attackable;
import org.camposmdev.util.FXUtil;

public class CharacterCardComponent extends Component implements CardRenderer<CharacterCard>, Playable, Attackable {
	private PlayerComponent player; /* this component is injected automatically */
	private Texture texture;

	@Override
	public void onAdded() {
		texture = FXUtil.loadCard(player.character().getImage().loResSrc());
		/* TODO - refactor code */
//		hp.current().addListener((o, arg0, arg1) -> {
//			if (arg1.intValue() <= 0) {
//				/* play death sound */
//				int i = (int) (Math.random() * 3) + 1;
//				FXGL.play(String.format("player/death%d.wav", i));
//				getDialogService().showMessageBox("You died", () -> {
//					getGameController().startNewGame();
//				});
//			} else if (arg1.intValue() < arg0.intValue()) {
//				/* play hurt sound */
//				int i = (int) (Math.random() * 3) + 1;
//				FXGL.play(String.format("player/hurt%d.wav", i));
//			}
//		});
		texture().setOnMouseClicked(event -> {
			texture().setRotate(texture().getRotate() + 90);
		});
		EntityService.get().events().add_onMouseHover_PreviewAndFeedBack(this);
	}

	@Override
	public void play() {
		/* TODO - Implement character abilities */
	}

	@Override
	public void damage(int arg0) {
		/* TODO - refactor code */
//		hp.current().set(hp.current().get() - arg0);
//		animationBuilder()
//				.duration(Duration.millis(50))
//				.repeat(10)
//				.autoReverse(true)
//				.fadeOut(texture())
//				.buildAndPlay();
	}

	@Override
	public void attack(Attackable arg0) {
		/* TODO - refactor code */
//		arg0.damage(atk.current().get());
	}

	@Override
	public CharacterCard card() {
		return player.character();
	}

	@Override
	public void setCard(CharacterCard card) {
		player.setCharacter(card);
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
		return FXUtil.loadCard(player.character().getImage().hiResSrc());
	}

	@Override
	public Texture loResTexture() {
		return FXUtil.loadCard(player.character().getImage().loResSrc());
	}
}
