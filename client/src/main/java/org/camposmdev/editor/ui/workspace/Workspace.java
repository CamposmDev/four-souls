package org.camposmdev.editor.ui.workspace;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.camposmdev.editor.ui.AppBar;
import org.camposmdev.editor.ui.CardPicker;
import org.camposmdev.editor.ui.NotificationBar;

public class Workspace {
    private final CardPicker cardPicker;
    private final BorderPane root;
    public Workspace(int width, int height) {
        cardPicker = new CardPicker(this);
        var appBar = new AppBar(this);
        var appBarBox = appBar.getContent();
        var notifyBox = NotificationBar.instance().getContent();
        root = new BorderPane(null, appBarBox, null, notifyBox, cardPicker.getContent());
        root.setPrefSize(width, height);
    }

    public void edit(String deckType) {
        cardPicker.edit(deckType);
    }

    public void set(IEditor editor) {
        root.setCenter(editor.getContent());
    }

    public Node getContent() {
        return root;
    }
}
