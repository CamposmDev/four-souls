package org.camposmdev.editor.ui.workspace;

import javafx.scene.Node;

public interface IEditor {
    void setId(String id);
    void setImage(String image);
    Node getContent();
}
