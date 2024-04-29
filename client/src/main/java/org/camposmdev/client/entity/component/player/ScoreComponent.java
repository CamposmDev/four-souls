package org.camposmdev.client.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.loot.LostSoulCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.util.Log;

public class ScoreComponent extends Component {
	private ObservableList<BaseCard> souls;

	@Override
	public void onAdded() {
		this.souls = FXCollections.observableArrayList();
	}

	public void add(BaseCard card) {
		if (card instanceof LostSoulCard || card instanceof BonusSoulCard)
			souls.add(card);
		else if (card instanceof MonsterCard monster && monster.getSoul() > 0) {
			souls.add(card);
		} else {
			Log.fatalf("Invalid soul card %s", card);
		}
	}

	public void remove(BaseCard card) {
		souls.remove(card);
	}

	public Integer count() {
		int sum = 0;
		for (BaseCard card : souls) {
			if (card instanceof LostSoulCard || card instanceof BonusSoulCard)
				sum++;
			if (card instanceof MonsterCard monster)
				sum += monster.getSoul();
		}
		return sum;
	}

	public void addListener(ListChangeListener<BaseCard> change) {
		souls.addListener(change);
	}
}
