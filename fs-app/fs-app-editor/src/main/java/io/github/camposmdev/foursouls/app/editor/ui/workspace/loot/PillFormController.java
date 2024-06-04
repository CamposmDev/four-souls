package io.github.camposmdev.foursouls.app.editor.ui.workspace.loot;

import io.github.camposmdev.foursouls.app.editor.ui.factory.DialogFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import io.github.camposmdev.foursouls.model.card.attribute.CardSet;
import io.github.camposmdev.foursouls.model.card.attribute.loot.PillEvent;
import io.github.camposmdev.foursouls.model.card.loot.PillCard;
import io.github.camposmdev.foursouls.model.fx.FXUtil;
import io.github.camposmdev.foursouls.model.fx.FormController;

import java.util.LinkedList;
import java.util.List;

public class PillFormController extends FormController<PillCard> {
    @FXML
    ComboBox<CardSet> cbCardSet;
    @FXML
    TextField tfRepeat;
    @FXML
    CheckBox cbShare;

    private List<PillEvent> pillEvents;

    @Override
    public void init() {
        FXUtil.initNumberFields(tfRepeat);
        pillEvents = new LinkedList<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
    }

    public void modPillEvents() {
        DialogFactory.instance().showPillEventModifierBox(pillEvents);
    }

    @Override
    public PillCard submit() throws Exception {
        var pill = new PillCard();
        pill.setCardSet(cbCardSet.getValue());
        pill.setEvents(pillEvents)
            .setRepeat(Byte.parseByte(tfRepeat.getText()))
            .setShare(cbShare.isSelected());
        /* clear pill events to make it more efficient for data entry of a new pill card */
        pillEvents = new LinkedList<>();
        return pill;
    }
}
