package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.CardVersion;
import io.github.camposmdev.foursouls.model.card.loot.LostSoulCard;
import io.github.camposmdev.foursouls.model.ui.FormController;

public class LostSoulFormController extends FormController<LostSoulCard> {
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
    public LostSoulCard submit() throws Exception {
        return (LostSoulCard) new LostSoulCard()
                .setVersion(cbVersion.getValue())
                .setCardSet(cbCardSet.getValue());
    }
}
