package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.camposmdev.util.Log;

public class HPComponent extends Component {
    private static final int DEFAULT_HP = 2;
    public IntegerProperty max;
    public final IntegerProperty current;

    public HPComponent(int max) {
        this.max = new SimpleIntegerProperty(max);
        this.current = new SimpleIntegerProperty(max);
    }

    @Override
    public void onAdded() {
        System.out.println("life added");
    }

    @Override
    public void onUpdate(double tpf) {
        if (current.get() <= 0) {
            Log.info("player died");
        }
    }
}
