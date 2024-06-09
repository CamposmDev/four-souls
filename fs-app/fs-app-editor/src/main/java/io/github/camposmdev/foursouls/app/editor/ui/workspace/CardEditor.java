package io.github.camposmdev.foursouls.app.editor.ui.workspace;

import javafx.scene.Node;
import io.github.camposmdev.foursouls.core.util.assets.CardAsset;

public interface CardEditor {
    String id();
    void setId(String id);
    CardAsset image();
    void setImage(CardAsset image);
    Node getContent();

    void commit();
}
