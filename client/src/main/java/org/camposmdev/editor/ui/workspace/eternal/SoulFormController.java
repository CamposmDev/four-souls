package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.eternal.SoulEternalCard;
import org.camposmdev.util.FormController;

public class SoulFormController extends FormController<SoulEternalCard> {
    @FXML CheckBox bone;
    @FXML ComboBox<CardVersion> cbVersion;

    @Override
    public void init() {
        cbVersion.setValue(CardVersion.V1);
        cbVersion.getItems().addAll(CardVersion.values());
    }

    @Override
    public SoulEternalCard submit() throws Exception {
        return new SoulEternalCard()
                .setTheBone(bone.isSelected())
                .setTheBone(cbVersion.getValue());
    }
}
