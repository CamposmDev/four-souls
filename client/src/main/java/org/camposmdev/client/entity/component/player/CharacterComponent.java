package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;
import org.camposmdev.client.entity.component.ATKComponent;
import org.camposmdev.client.entity.component.HPComponent;
import org.camposmdev.client.entity.component.Playable;
import org.camposmdev.client.entity.component.card.CardComponent;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.game.Attackable;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class CharacterComponent extends CardComponent implements Playable, Attackable {
	private HPComponent hp; /* this component is injected automatically */
	private ATKComponent atk; /* this component is injected automatically */

	public CharacterComponent(CharacterCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
		hp.max().set(card().getHp());
		hp.current().set(card().getHp());
		hp.current().addListener((o, arg0, arg1) -> {
			if (arg1.intValue() <= 0) {
				/* play death sound */
				int i = (int) (Math.random() * 3) + 1;
				FXGL.play(String.format("player/death%d.wav", i));
				getDialogService().showMessageBox("You died", () -> {
					getGameController().startNewGame();
				});
			} else if (arg1.intValue() < arg0.intValue()) {
				/* play hurt sound */
				int i = (int) (Math.random() * 3) + 1;
				FXGL.play(String.format("player/hurt%d.wav", i));
			}
		});
		atk.max().set(card().getAtk());
		atk.current().set(card().getAtk());
		texture().setOnMouseClicked(event -> {
			texture().setRotate(texture().getRotate() + 90);
		});
		EntityService.get().events().add_onMouseHover_Feedback(this);
	}

	@Override
	public CharacterCard card() {
		return (CharacterCard) super.card();
	}

	@Override
	public void play() {
		/* TODO - Implement character abilities */
	}

	@Override
	public void damage(int arg0) {
		getNotificationService().pushNotification("Damage incoming!");
		hp.current().set(hp.current().get() - arg0);
		animationBuilder()
				.duration(Duration.millis(50))
				.repeat(10)
				.autoReverse(true)
				.fadeOut(texture())
				.buildAndPlay();

	}

	@Override
	public void attack(Attackable arg0) {
		arg0.damage(atk.current().get());
	}
}
