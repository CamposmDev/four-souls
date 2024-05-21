package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.collections.ListChangeListener;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.loot.LostSoulCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.util.Log;

public class ScoreComponent extends Component {
	private PlayerComponent player;

	public void add(BaseCard card) {
		if (card instanceof LostSoulCard || card instanceof BonusSoulCard)
			player.get().souls().add(card);
		else if (card instanceof MonsterCard monster && monster.getSoul() > 0) {
			player.get().souls().add(card);
		} else {
			Log.fatalf("Invalid soul card %s", card);
		}
	}

	public void remove(BaseCard card) {
		player.get().souls().remove(card);
	}

	public Integer count() {
		int sum = 0;
		for (BaseCard card : player.get().souls()) {
			if (card instanceof LostSoulCard || card instanceof BonusSoulCard)
				sum++;
			if (card instanceof MonsterCard monster)
				sum += monster.getSoul();
		}
		return sum;
	}

	public void addListener(ListChangeListener<BaseCard> change) {
		player.get().souls().addListener(change);
	}
}
