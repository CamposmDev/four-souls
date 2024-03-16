package org.camposmdev.client.game.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LifeComponent extends Component {
    private static final int DEFAULT_HP = 2;
    private final IntegerProperty hp;

    public LifeComponent() {
        this.hp = new SimpleIntegerProperty(DEFAULT_HP);
    }

    public IntegerProperty hpProperty() {
        return hp;
    }

    @Override
    public void onUpdate(double tpf) {
        if (hp.get() <= 0) {
            System.out.println("played died");
        }
    }
}
