package io.github.camposmdev.foursouls.model.game.player;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.bsoul.BonusSoulCard;
import io.github.camposmdev.foursouls.model.card.loot.LostSoulCard;
import io.github.camposmdev.foursouls.model.card.monster.MonsterCard;

public class Souls {
	private final ObservableList<BaseCard> souls;

	public Souls() {
		this.souls = FXCollections.observableArrayList();
	}

	public boolean add(BaseCard card) {
		if (card instanceof LostSoulCard || card instanceof BonusSoulCard) {
			return souls.add(card);
		} else if (card instanceof MonsterCard monster && monster.hasSouls()) {
			return souls.add(card);
		}
		return false;
	}

	public boolean remove(BaseCard card) {
		return souls.remove(card);
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
