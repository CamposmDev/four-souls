package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import org.camposmdev.client.entity.component.ATKComponent;
import org.camposmdev.client.entity.component.HPComponent;
import org.camposmdev.client.service.EntityService;
import org.camposmdev.client.ui.view.PlayerHUDView;


public class PlayerHUDComponent extends Component {
	private PlayerHUDView hud;

	@Override
	public void onAdded() {
		hud = new PlayerHUDView(entity);
		/* display the player's hp, atk, and other attributes */
		entity.getComponentOptional(HPComponent.class).ifPresent(comp -> {
			hud.status().setHeartText((comp.current().getValue()));
			comp.current().addListener((o,arg0,arg1) -> hud.status().setHeartText(arg1.intValue()));
		});
		/* display atk attribute and add ch	ange listener */
		entity.getComponentOptional(ATKComponent.class).ifPresent(comp -> {
			hud.status().setAttackText(comp.current().getValue());
			comp.current().addListener((o,arg0,arg1) -> hud.status().setAttackText(arg1.intValue()));
		});
		/* display cents attribute and add change listener */
		entity.getComponentOptional(MoneyComponent.class).ifPresent(comp -> {
			hud.status().setPennyText(comp.cents().getValue());
			comp.cents().addListener((o,arg0,arg1) -> hud.status().setPennyText(arg1.intValue()));
		});
		/* display soul attribute and add change listener */
		entity.getComponentOptional(PlayerSoulComponent.class).ifPresent(comp -> {
			hud.status().setSoulText(comp.souls());
			comp.addListener(change -> hud.status().setSoulText(comp.souls()));
		});
		/* display changes when player draws and discard loot cards */
		entity.getComponentOptional(PlayerLootComponent.class).ifPresent(comp -> {
			comp.addListener(change -> {
				while (change.next()) {
					if (change.wasAdded()) {
						change.getAddedSubList().forEach(loot -> {
							var es = FXGL.getService(EntityService.class);
							es.events().onMouseHover_Preview(loot);
							es.events().onMouseHover_Highlight(loot);
							loot.texture().setOnMouseClicked(event -> {
								comp.loot().remove(loot);
							});
							hud.loot().add(loot.texture());
						});
					} else if (change.wasRemoved()) {
						change.getRemoved().forEach(loot -> {
							hud.loot().remove(loot.texture());
							loot.getEntity().removeFromWorld();
						});
					}
				}
			});
		});
		/* display changes when players buy and discard items */
		entity.getComponentOptional(PlayerItemsComponent.class).ifPresent(comp -> {
			comp.addListener(change -> {
				while (change.next()) {
					if (change.wasAdded()) {
						change.getAddedSubList().forEach(item -> {
							hud.items().add(item.texture());
						});
					} else if (change.wasRemoved()) {
						change.getRemoved().forEach(item -> {
							hud.items().remove(item.texture());
							item.getEntity().removeFromWorld();
						});
					}
				}
			});
		});
		hud.render();
	}

	public PlayerHUDView view() {
		return hud;
	}
}
