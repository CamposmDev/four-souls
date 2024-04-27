package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.camposmdev.util.Log;

/**
 * How much HP does the entity have?
 */
public class HPComponent extends Component implements AttributeComponent<IntegerProperty> {
    private static final int DEFAULT_HP = 2;
    private IntegerProperty max;
    private IntegerProperty current;

    @Override
    public void onAdded() {
        this.max = new SimpleIntegerProperty(DEFAULT_HP);
        this.current = new SimpleIntegerProperty(DEFAULT_HP);
    }

    @Override
    public void onUpdate(double tpf) {
        if (current.get() <= 0) {
            Log.info("entity died");
        }
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
