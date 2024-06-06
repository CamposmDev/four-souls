package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.CardVersion;
import io.github.camposmdev.foursouls.core.card.loot.SoulHeartCard;
import io.github.camposmdev.foursouls.core.ui.FormController;

public class SoulHeartFormController extends FormController<SoulHeartCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    ComboBox<CardVersion> cbVersion;
    @Override
    public void init() {
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbVersion.setValue(CardVersion.UNDEFINED);
        cbVersion.getItems().addAll(CardVersion.values());
    }

    @Override
    public SoulHeartCard submit() throws Exception {
        return (SoulHeartCard) new SoulHeartCard()
                .setVersion(cbVersion.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
