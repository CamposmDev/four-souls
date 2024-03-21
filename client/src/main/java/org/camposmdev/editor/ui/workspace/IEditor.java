package org.camposmdev.editor.ui.workspace;

import javafx.scene.Node;

public interface IEditor {
    String id();
    void setId(String id);
    String image();
    void setImage(String image);
    Node getContent();

    void commit();
}
