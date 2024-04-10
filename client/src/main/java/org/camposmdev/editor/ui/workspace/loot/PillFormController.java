package org.camposmdev.editor.ui.workspace.loot;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.attribute.loot.PillEvent;
import org.camposmdev.model.card.loot.PillCard;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class PillFormController extends FormController<PillCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfRepeat;
    @FXML
    CheckBox cbShare;
    @FXML
    ComboBox<CardVersion> cbVersion;

    private List<PillEvent> pillEvents;

    @Override
    public void init() {
        pillEvents = new LinkedList<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        cbVersion.setValue(CardVersion.UNDEFINED);
        cbVersion.getItems().addAll(CardVersion.values());
    }

    public void modPillEvents() {
        DialogFactory.instance().showPillEventModifierBox(pillEvents);
    }

    @Override
    public PillCard submit() throws Exception {
        var pill = new PillCard();
        pill.setCardSet(cbCardSet.getValue());
        pill.setRepeat(Byte.parseByte(tfRepeat.getText()));
        pill.setShare(cbShare.isSelected());
        pill.setVersion(cbVersion.getValue());
        return pill;
    }
}
