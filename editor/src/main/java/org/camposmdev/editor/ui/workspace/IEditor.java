package org.camposmdev.editor.ui.workspace;

import javafx.scene.Node;
import org.camposmdev.model.atlas.ImageInfo;

public interface IEditor {
    String id();
    void setId(String id);
    ImageInfo image();
    void setImage(ImageInfo image);
    Node getContent();

    void commit();
}
