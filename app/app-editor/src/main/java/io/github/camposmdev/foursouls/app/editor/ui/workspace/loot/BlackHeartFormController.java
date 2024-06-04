package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.CardVersion;
import io.github.camposmdev.foursouls.model.card.loot.BlackHeartCard;
import io.github.camposmdev.foursouls.model.util.fx.FormController;

public class BlackHeartFormController extends FormController<BlackHeartCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;

    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    @Override
    public BlackHeartCard submit() throws Exception {
        return (BlackHeartCard) new BlackHeartCard()
                .setCardSet(cbCardSet.getValue());
    }
}
