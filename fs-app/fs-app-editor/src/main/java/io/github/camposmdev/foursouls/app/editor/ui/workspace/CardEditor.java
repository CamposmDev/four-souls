package io.github.camposmdev.foursouls.app.editor.ui.workspace;

import javafx.scene.Node;
import io.github.camposmdev.foursouls.core.card.attribute.ImageInfo;

public interface CardEditor {
    String id();
    void setId(String id);
    ImageInfo image();
    void setImage(ImageInfo image);
    Node getContent();

    void commit();
}
