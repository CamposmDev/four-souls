package org.camposmdev.editor.ui.workspace;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.CardViewer;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.character.CharacterCard;

public class CharacterEditor extends BaseEditor {
    private final GridPane root;
    private final CardViewer cv;
    private final TextField tfHealth;
    private final TextField tfDamage;
    private final ListView<String> lvEternal;
    private final Button btSubmit;

    private Image currentImage;

    public CharacterEditor() {
        var eternalKeys = FXCollections.observableArrayList(Model.instance().images().allEternalKeys());
        cv = new CardViewer();
        tfHealth = new TextField();
        tfHealth.setPromptText(String.format("[%d, %d]", Byte.MIN_VALUE, Byte.MAX_VALUE));
        tfDamage = new TextField();
        tfDamage.setPromptText(String.format("[%d, %d]", Byte.MIN_VALUE, Byte.MAX_VALUE));
        lvEternal = new ListView<>(eternalKeys);
        lvEternal.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvEternal.getSelectionModel().selectedItemProperty().addListener((ov, arg0, arg1) -> {
            currentImage = Model.instance().images().loadSource2(CardType.ETERNAL, arg1);
            cv.setImage(currentImage);
        });
        lvEternal.setCellFactory(lv -> {
            var cell = new ListCell<String>() {
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    setText(s);
                }
            };
            cell.hoverProperty().addListener((ov, wasHovered, isHovered) -> {
                if (isHovered) {
                    if (!cell.isEmpty()) {
                        cv.setImage(Model.instance().images().loadSource2(CardType.ETERNAL, cell.getItem()));
                    }
                }
                if (wasHovered) {
                    if (currentImage != null) {
                        cv.setImage(currentImage);
                    } else {
                        cv.setImage(null);
                    }
                }
            });
            return cell;
        });
        lvEternal.getSelectionModel().selectFirst();
        btSubmit = new Button("Submit");
        btSubmit.setOnAction(e -> commit());
        root = new GridPane(12, 12);
//        root.setAlignment(Pos.CENTER_LEFT);
        root.addColumn(0, new Label("Hit Points"),
                new Label("Damage"),
                new Label("Eternal ID"));
        root.addColumn(1, tfHealth, tfDamage, lvEternal);
        root.add(cv.getContent(), 2, 0, 1, 4);
        root.add(btSubmit, 0, 3);
    }

    private void build() {
        try {
            /* build CharacterSheet object */
            var hitPoints = Byte.parseByte(tfHealth.getText());
            var damage = Byte.parseByte(tfDamage.getText());
            var eternalId = lvEternal.getSelectionModel().getSelectedItem();
            var card = new CharacterCard();
            card.setId(super.id()).setImage(super.image());
            card.setHitPoints(hitPoints).setDamage(damage).setEternalId(eternalId);
            /* add the character sheet to our card atlas */
            Model.instance().cards().addCharacter(card);
            NotificationBar.instance().push(card.toString());
        } catch (Exception ex) {
            DialogFactory.instance().showErrorBox(ex);
        }
    }

    @Override
    public Node getContent() {
        return root;
    }

    @Override
    public void commit() {
        build();
    }
}
