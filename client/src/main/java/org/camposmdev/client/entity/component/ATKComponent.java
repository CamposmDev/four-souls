package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * How much attack damage does the entity do?
 */
public class ATKComponent extends Component implements AttributeComponent<IntegerProperty> {
	private static final int DEFAULT_ATK = 1;
	private IntegerProperty max;
	private IntegerProperty current;

	@Override
	public void onAdded() {
		this.max = new SimpleIntegerProperty(DEFAULT_ATK);
		this.current = new SimpleIntegerProperty(DEFAULT_ATK);
	}

	@Override
	public IntegerProperty max() {
		return max;
	}

	@Override
	public IntegerProperty current() {
		return current;
	}
}
