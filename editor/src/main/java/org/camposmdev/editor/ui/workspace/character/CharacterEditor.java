package org.camposmdev.editor.ui.workspace.character;

import com.almasb.fxgl.dsl.FXGL;
import org.camposmdev.editor.ui.CardViewer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.camposmdev.editor.model.Model;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.editor.ui.factory.DialogFactory;
import org.camposmdev.editor.ui.workspace.BaseEditor;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.util.FXUtil;

public class CharacterEditor extends BaseEditor {
    private final GridPane root;
    private final CardViewer cv;
    private final ComboBox<CardSet> cbCardSet;
    private final TextField tfHealth;
    private final TextField tfDamage;
    private final ListView<String> lvEternal;

    private Image currentImage;

    public CharacterEditor() {
        var eternalKeys = FXCollections.observableArrayList(Model.instance().images().allEternalKeys());
        cv = new CardViewer();
        cbCardSet = new ComboBox<>();
        cbCardSet.setValue(CardSet.UNDEFINED);
        cbCardSet.getItems().addAll(CardSet.values());
        tfHealth = new TextField();
        tfDamage = new TextField();
        FXUtil.initNumberFields(tfHealth, tfDamage);
        lvEternal = new ListView<>(eternalKeys);
        lvEternal.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvEternal.getSelectionModel().selectedItemProperty().addListener((ov, arg0, arg1) -> {
            /* if an eternal item is unselected, do not try to fetch its image */
            if (arg1 == null) return;
            var src = Model.instance().images().source2(CardType.ETERNAL, arg1);
            assert src != null;
            currentImage = FXGL.image(src);
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
                        var src = Model.instance().images().source2(CardType.ETERNAL, cell.getItem());
                        assert src != null;
                        var img = FXGL.image(src);
                        cv.setImage(img);
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
        Button btCommit = new Button("Commit");
        btCommit.setOnAction(e -> commit());
        root = new GridPane(4, 4);
        root.setPadding(new Insets(2));
        root.addColumn(0, new Label("Card Set"),
                new Label("HP"),
                new Label("ATK"),
                new Label("Eternal ID"));
        root.addColumn(1, cbCardSet, tfHealth, tfDamage, lvEternal);
        root.add(cv.getContent(), 2, 0, 1, 4);
        root.add(btCommit, 0, 4);
    }

    private void build() {
        try {
            /* build CharacterSheet object */
            var hitPoints = Byte.parseByte(tfHealth.getText());
            var damage = Byte.parseByte(tfDamage.getText());
            var eternalId = lvEternal.getSelectionModel().getSelectedItem();
            var card = new CharacterCard();
            card.setId(super.id()).setImage(super.image()).setCardSet(cbCardSet.getValue());
            card.setHitPoints(hitPoints).setDamage(damage).setEternalId(eternalId);
            /* add the character card to our card atlas */
            Model.instance().cards().add(card);
            NotificationBar.instance().push(card);
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
