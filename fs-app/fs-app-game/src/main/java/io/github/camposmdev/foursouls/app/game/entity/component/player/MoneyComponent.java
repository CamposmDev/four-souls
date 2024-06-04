package io.github.camposmdev.foursouls.app.game.entity.component.player;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MoneyComponent extends Component {
	private static final byte DEFAULT_CENTS = 3;
	private IntegerProperty cents;

	@Override
	public void onAdded() {
		this.cents = new SimpleIntegerProperty(DEFAULT_CENTS);
	}

	public IntegerProperty cents() {
		return cents;
	}
}
