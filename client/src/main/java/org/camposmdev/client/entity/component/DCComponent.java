package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.SimpleIntegerProperty;

public class DCComponent extends Component implements IAttribute<SimpleIntegerProperty> {
	private static final int DEFAULT_DC = 3;
	private SimpleIntegerProperty max, current;

	@Override
	public void onAdded() {
		this.max = new SimpleIntegerProperty(DEFAULT_DC);
		this.current = new SimpleIntegerProperty(DEFAULT_DC);
	}

	@Override
	public SimpleIntegerProperty max() {
		return null;
	}

	@Override
	public SimpleIntegerProperty current() {
		return null;
	}
}
