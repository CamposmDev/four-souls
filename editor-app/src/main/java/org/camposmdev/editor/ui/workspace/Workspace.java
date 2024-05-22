package org.camposmdev.editor.ui.workspace;

import org.camposmdev.editor.ui.CardPicker;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.camposmdev.editor.ui.AppBar;
import org.camposmdev.editor.ui.NotificationBar;
import org.camposmdev.model.card.attribute.CardType;

public class Workspace {
    private final CardPicker cardPicker;
    private final BorderPane root;
    private final BorderPane centerPane;
    public Workspace(int width, int height) {
        cardPicker = new CardPicker(this);
        var appBar = AppBar.instance(this).getContent();
        var notifyBox = NotificationBar.instance().getContent();
        centerPane = new BorderPane();
        centerPane.setBottom(notifyBox);
        root = new BorderPane(centerPane, appBar, null, null, cardPicker.getContent());
        root.setPrefSize(width, height);
    }

    public void edit(CardType cardType) {
        cardPicker.edit(cardType);
    }

    public void set(CardEditor editor) {
        centerPane.setCenter(editor.getContent());
    }

    public Node getContent() {
        return root;
    }
}
