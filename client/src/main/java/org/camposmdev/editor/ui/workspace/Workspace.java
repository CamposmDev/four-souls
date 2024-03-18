package org.camposmdev.editor.ui.workspace;

import javafx.scene.layout.*;
import org.camposmdev.editor.ui.AppBar;
import org.camposmdev.editor.ui.CardPicker;
import org.camposmdev.editor.ui.NotificationBar;

public class Workspace {
    private final BorderPane root;
    private final CardPicker cardChooser;
    public Workspace(int width, int height) {
        root = new BorderPane();
        root.setPrefSize(width, height);
        var appBar = new AppBar(this);
        cardChooser = new CardPicker(this);
        root.setTop(appBar.getContent());
        root.setLeft(cardChooser.getContent());
        root.setBottom(NotificationBar.instance().getContent());
    }

    public void edit(String deckType) {
        cardChooser.edit(deckType);
    }

    public void set(IEditor editor) {
        root.setCenter(editor.getContent());
    }

    public BorderPane getContent() {
        return root;
    }

    public void clear() {
        root.setCenter(null);
    }
}
