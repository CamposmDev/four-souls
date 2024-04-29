package org.camposmdev.client.entity.component;

import com.almasb.fxgl.entity.component.Component;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;


/**
 * How much HP does the entity have?
 */
public class HPComponent extends Component implements IAttribute<IntegerProperty> {
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
            getDialogService().showMessageBox("You died", () -> {
                getGameController().exit();
            });
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
