package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.camposmdev.client.ui.view.PlayerHUDView;

import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;


public class PlayerHUDComponent extends Component {
	private PlayerHUDView hud;
	private PlayerComponent player;
	private PlayerLootComponent loot; /* this component is injected automatically */
	private PlayerItemsComponent items; /* this component is injected automatically */

	@Override
	public void onAdded() {
		hud = new PlayerHUDView(entity);
		/* display the player's hp, atk, and other attributes */
		hud.status().setHeartText(player.get().hp().current().get());
		player.get().hp().current().addListener((o,arg0,arg1) -> hud.status().setHeartText(arg1));
//		hud.status().setHeartText(hp.current().get());
//		hp.current().addListener((o,arg0,arg1) -> hud.status().setHeartText(arg1.intValue()));
		/* display atk attribute and add ch	ange listener */
		hud.status().setAttackText(player.get().atk().current().get());
		player.get().atk().current().addListener((o,arg0,arg1) -> hud.status().setAttackText(arg1));
//		hud.status().setAttackText(atk.current().get());
//		atk.current().addListener((o,arg0,arg1) -> hud.status().setAttackText(arg1.intValue()));
		/* display cents attribute and add change listener */
		hud.status().setPennyText(player.get().cents().get());
		player.get().cents().addListener((o,arg0,arg1) -> hud.status().setPennyText(arg1.intValue()));
//		hud.status().setPennyText(money.cents().get());
//		money.cents().addListener((o,arg0,arg1) -> hud.status().setPennyText(arg1.intValue()));
		/* display soul attribute and add change listener */
		hud.status().setSoulText(player.get().souls().count());
		player.get().souls().addListener(change -> hud.status().setSoulText(player.get().souls().count()));
//		hud.status().setSoulText(score.count());
//		score.addListener(change -> hud.status().setSoulText(score.count()));
		/* display changes when player draws or discard loot */
		loot.addListener(change -> {
			while (change.next()) {
				if (change.wasAdded()) {
					change.getAddedSubList().forEach(x -> {
						x.texture().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
							loot.inventory().remove(x);
						});
						hud.loot().add(x.texture());
						animationBuilder()
								.duration(Duration.millis(250))
								.rotate(x.texture())
								.origin(new Point3D(0, 1, 0))
//								.url(new Point2D(0,1))
								.from(new Point3D(0,90,0))
								.to(new Point3D(0,0,0))
//								.from(90)
//								.to(0)
								.buildAndPlay();

//						var anim = EntityService.get().events().rotateIn(x.texture());
//						anim.setOnFinished(e -> {  });
//						anim.play();
					});
				} else if (change.wasRemoved()) {
					change.getRemoved().forEach(x -> {
						hud.loot().remove(x.texture());
						x.getEntity().removeFromWorld();
					});
				}
			}
		});
		/* display changes when players buys or discard items */
		items.addListener(change -> {
			while (change.next()) {
				if (change.wasAdded()) {
					change.getAddedSubList().forEach(x ->
							hud.items().add(x.texture()));
				} else if (change.wasRemoved()) {
					change.getRemoved().forEach(x -> {
						hud.items().remove(x.texture());
						x.getEntity().removeFromWorld();
					});
				}
			}
		});
		hud.render();
	}

	@Override
	public void onUpdate(double tpf) {

	}

	public PlayerHUDView view() {
		return hud;
	}
}
