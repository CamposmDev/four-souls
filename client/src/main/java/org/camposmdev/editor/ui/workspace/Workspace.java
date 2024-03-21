package org.camposmdev.editor.ui.workspace;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.camposmdev.editor.ui.AppBar;
import org.camposmdev.editor.ui.CardPicker;
import org.camposmdev.editor.ui.NotificationBar;

public class Workspace {
    private final AnchorPane root;
    private final HBox content;
    private final CardPicker cardPicker;
    public Workspace(int width, int height) {
        root = new AnchorPane();
        root.setPrefSize(width, height);
        content = new HBox(8);
        root.getChildren().add(content);
        AnchorPane.setTopAnchor(content, 30d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setBottomAnchor(content, 30d);
        AnchorPane.setLeftAnchor(content, 0d);

        var appBar = new AppBar(this);
        var appBarBox = appBar.getContent();
        root.getChildren().add(appBarBox);
        AnchorPane.setLeftAnchor(appBarBox, 0d);
        AnchorPane.setRightAnchor(appBarBox, 0d);

        var notifyBox = NotificationBar.instance().getContent();
        root.getChildren().add(notifyBox);
        AnchorPane.setBottomAnchor(notifyBox, 0d);
        AnchorPane.setLeftAnchor(notifyBox, 0d);
        AnchorPane.setRightAnchor(notifyBox, 0d);

        cardPicker = new CardPicker(this);
    }

    public void edit(String deckType) {
        cardPicker.edit(deckType);
    }

    public void set(IEditor editor) {
        content.getChildren().add(editor.getContent());
    }

    public AnchorPane getContent() {
        return root;
    }

    public void clear() {
        content.getChildren().clear();
        content.getChildren().add(cardPicker.getContent());
    }
}
