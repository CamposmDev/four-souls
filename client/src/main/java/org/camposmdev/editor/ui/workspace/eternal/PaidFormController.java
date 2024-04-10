package org.camposmdev.editor.ui.workspace.eternal;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.eternal.PaidEternalCard;
import org.camposmdev.util.FormController;

public class PaidFormController extends FormController<PaidEternalCard> {
    @FXML CheckBox cbIsTrash;
    @FXML ComboBox<CardVersion> cbTrashVersion;
    @FXML CheckBox cbIsBagOfCrafting, cbIsIBS, cbIsSumpotrium, cbIsLilSteven;
    @FXML CheckBox cbIsSpelunkingPack, cbIsLemegeton, cbIsSpindownDice;

    @Override
    public void init() {
        cbTrashVersion.setValue(CardVersion.V1);
        cbTrashVersion.getItems().addAll(CardVersion.values());
    }

    @Override
    public PaidEternalCard submit() throws Exception {
        return new PaidEternalCard()
                .setBagOTrash(cbIsTrash.isSelected())
                .setBagOTrash(cbTrashVersion.getValue())
                .setBagOfCrafting(cbIsBagOfCrafting.isSelected())
                .setIBS(cbIsIBS.isSelected())
                .setSumpotrium(cbIsSumpotrium.isSelected())
                .setLilSteven(cbIsLilSteven.isSelected())
                .setSpelunkingPack(cbIsSpelunkingPack.isSelected())
                .setLemegeton(cbIsLemegeton.isSelected())
                .setSpindownDice(cbIsSpindownDice.isSelected());
    }
}
