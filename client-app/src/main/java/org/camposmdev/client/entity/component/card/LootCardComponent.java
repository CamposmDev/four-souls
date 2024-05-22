package org.camposmdev.client.entity.component.card;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.camposmdev.client.entity.EntityType;
import org.camposmdev.client.entity.component.Playable;
import org.camposmdev.client.entity.component.player.MoneyComponent;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.model.card.loot.KeyCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.loot.MoneyCard;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getNotificationService;

public class LootCardComponent extends CardComponent implements Playable {
	public LootCardComponent(LootCard card) {
		super(card);
	}

	@Override
	public void onAdded() {
		super.onAdded();
		var es = EntityService.get();
		es.events().add_onMouseHover_Feedback(this);
		texture().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> play());
	}

	@Override
	public LootCard card() {
		return (LootCard) super.card;
	}

	@Override
	public void play() {
		switch (card().getCardType()) {
			case MONEY1C, MONEY2C, MONEY3C, MONEY4C -> {
				var card = (MoneyCard) super.card;
				byte value = card.getValue();
				var entity = getGameWorld().getSingleton(EntityType.PLAYER);
				var comp = entity.getComponent(MoneyComponent.class);
				FXGL.run(() -> {
					comp.cents().set(comp.cents().get() + 1);
					FXGL.play("feedback/penny pickup.wav");
				}, Duration.millis(200), value);
			}
			case MONEY5C -> {
				var card = (MoneyCard) super.card;
				byte value = card.getValue();
				var entity = getGameWorld().getSingleton(EntityType.PLAYER);
				var comp = entity.getComponent(MoneyComponent.class);
				comp.cents().set(comp.cents().get() + value);
				FXGL.play("feedback/nickel pickup.wav");
			}
			case MONEY10C -> {
				var card = (MoneyCard) super.card;
				byte value = card.getValue();
				var entity = getGameWorld().getSingleton(EntityType.PLAYER);
				var comp = entity.getComponent(MoneyComponent.class);
				comp.cents().set(comp.cents().get() + value);
				FXGL.play("feedback/dime pickup.wav");
			}
			case KEYS -> {
				var card = (KeyCard) super.card;
				switch (card.getKind()) {
					case KEY -> {
						/* TODO - Implement their card effects */
						FXGL.play("feedback/key pickup guantlet 4.wav");
					}
					case GOLD_KEY -> {
						/* TODO - Implement their card effects */
						FXGL.play("feedback/golden key.wav");
					}
					default -> getNotificationService()
							.pushNotification("Unsupported card: " + card.getId());
				}
			}
			default -> getNotificationService()
					.pushNotification("Unsupported card: " + card.getId());
		}
	}
}
