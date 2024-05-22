package org.camposmdev.editor.ui;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.util.FXUtil;
import org.camposmdev.util.FormController;

import java.util.LinkedList;
import java.util.List;

public class AttributeModifierBox extends FormController<AttributeModifier> {
    private final ComboBox<AttributeType> thresholdTypeComboBox;
    private final TextField thresholdValueTextField;
    private final ComboBox<ThresholdCompare> thresholdCompareComboBox;
    private final CheckBox everyOtherTimeCheckBox;
    private final ComboBox<AttributeType> modTypeComboBox;
    private final TextField modValueTextField;

    private final List<RollEvent> events;

    public AttributeModifierBox() {
        events = new LinkedList<>();
        thresholdTypeComboBox = new ComboBox<>();
        thresholdValueTextField = new TextField();
        thresholdCompareComboBox = new ComboBox<>();
        everyOtherTimeCheckBox = new CheckBox();
        modTypeComboBox = new ComboBox<>();
        modValueTextField = new TextField();
        FXUtil.initNumberFields(thresholdValueTextField, modValueTextField);
    }

    public void load(AttributeModifier modifier) {
        thresholdTypeComboBox.setValue(modifier.getThresholdType());
        thresholdValueTextField.setText(modifier.getThresholdValue().toString());
        thresholdCompareComboBox.setValue(modifier.getCompare());
        everyOtherTimeCheckBox.setSelected(modifier.isEveryOtherTime());
        modTypeComboBox.setValue(modifier.getModType());
        modValueTextField.setText(modifier.getModValue().toString());
        events.clear();
        events.addAll(modifier.getEvents());
    }

    @Override
    public AttributeModifier submit() throws Exception {
        AttributeModifier modifier = new AttributeModifier();
        modifier.setThresholdType(thresholdTypeComboBox.getValue());
        modifier.setThresholdValue(Byte.parseByte(thresholdValueTextField.getText()));
        modifier.setCompare(thresholdCompareComboBox.getValue());
        modifier.setEveryOtherTime(everyOtherTimeCheckBox.isSelected());
        modifier.setModType(modTypeComboBox.getValue());
        modifier.setModValue(Byte.parseByte(modValueTextField.getText()));
        modifier.setEvents(events);
        return modifier;
    }

    public Node getContent() {
        GridPane gridPane = new GridPane(4,4);
        // Variable definitions
        ComboBox<AttributeType> thresholdTypeComboBox = new ComboBox<>();
        thresholdTypeComboBox.getItems().addAll(AttributeType.values());
        TextField thresholdValueTextField = new TextField();
        thresholdValueTextField.setPromptText("[0,127]");
        ComboBox<ThresholdCompare> thresholdCompareComboBox = new ComboBox<>();
        thresholdCompareComboBox.getItems().addAll(ThresholdCompare.values());
        CheckBox everyOtherTimeCheckBox = new CheckBox();
        ComboBox<AttributeType> modTypeComboBox = new ComboBox<>();
        modTypeComboBox.getItems().addAll(AttributeType.values());
        TextField modValueTextField = new TextField();
        modValueTextField.setPromptText("[0,127]");
        Button eventsButton = new Button("Modify");
        eventsButton.setOnAction(e -> DialogFactory.instance().showRollEventModifierBox(events));

        // Adding controls to the GridPane
        gridPane.addRow(0, new Label("thresholdType"), thresholdTypeComboBox);
        gridPane.addRow(1, new Label("thresholdValue"), thresholdValueTextField);
        gridPane.addRow(2, new Label("thresholdCompare"), thresholdCompareComboBox);
        gridPane.addRow(3, new Label("everyOtherTime"), everyOtherTimeCheckBox);
        gridPane.addRow(4, new Label("modType"), modTypeComboBox);
        gridPane.addRow(5, new Label("modValue"), modValueTextField);
        gridPane.addRow(6, new Label("rollEvents"), eventsButton);
        return gridPane;
    }
}
