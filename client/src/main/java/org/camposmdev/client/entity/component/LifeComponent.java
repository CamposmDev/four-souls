package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.camposmdev.util.Log;

public class LifeComponent extends Component {
    private static final int DEFAULT_HP = 2;
    public final IntegerProperty value;

    public LifeComponent() {
        this.value = new SimpleIntegerProperty(DEFAULT_HP);
    }

    @Override
    public void onAdded() {
        System.out.println("life added");
    }

    @Override
    public void onUpdate(double tpf) {
        if (value.get() <= 0) {
            Log.info("player died");
        }
    }
}
