package io.github.camposmdev.foursouls.app.editor.ui.workspace.character;

import com.almasb.fxgl.dsl.FXGL;
import io.github.camposmdev.foursouls.app.editor.model.Model;
import io.github.camposmdev.foursouls.app.editor.ui.CardViewer;
import io.github.camposmdev.foursouls.core.card.attribute.CardSet;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.character.SpecialType;
import io.github.camposmdev.foursouls.core.card.character.CharacterCard;
import io.github.camposmdev.foursouls.core.ui.FXUtil;
import io.github.camposmdev.foursouls.core.ui.FormController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class CharacterFormController extends FormController<CharacterCard> {
    @FXML private ComboBox<CardSet> cardSet;
    @FXML private TextField hitPoints, damage;
    @FXML private ListView<String> eternalIdListView;
    @FXML private ComboBox<SpecialType> special;
    @FXML private GridPane root;
    private final CardViewer cv;
    private Image currentImage;

    public CharacterFormController() {
        cv = new CardViewer();
        currentImage = null;
    }

    @Override
    public void init() {
        cardSet.setValue(CardSet.UNDEFINED);
        cardSet.getItems().addAll(CardSet.values());
        FXUtil.initNumberFields(hitPoints, damage);
//        var eternalIds = FXCollections.observableArrayList(Model.instance().getAssetsByType())
        var eternalMap = Model.instance().assets().getAssetsByCategory(CardType.ETERNAL);
        var eternalIds = eternalMap.keySet();
        eternalIdListView.getItems().setAll(eternalIds);
        eternalIdListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        eternalIdListView.getSelectionModel().selectedItemProperty().addListener((ov, arg0, arg1) -> {
            /* if an eternal item is unselected, do not try to fetch its image */
            if (arg1 == null) return;
            var src = eternalMap.get(arg1).hiResSrc();
            currentImage = FXGL.image(src);
            cv.setImage(currentImage);
        });
        eternalIdListView.setCellFactory(lv -> {
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
                        var src = eternalMap.get(cell.getItem()).hiResSrc();
//                        var src = Model.instance().assets().source2(CardType.ETERNAL, cell.getItem());
//                        assert src != null;
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
        eternalIdListView.getSelectionModel().selectFirst();
        special.setValue(SpecialType.DEFAULT);
        special.getItems().addAll(SpecialType.values());
        root.add(cv.getContent(), 2, 0, 1, 5);
    }


    @Override
    public CharacterCard submit() throws Exception {
        return (CharacterCard) new CharacterCard()
                .setHp(Byte.parseByte(hitPoints.getText()))
                .setAtk(Byte.parseByte(damage.getText()))
                .setEternalId(eternalIdListView.getSelectionModel().getSelectedItem())
                .setSpecial(special.getValue())
                .setCardSet(cardSet.getValue());
    }
}
