package org.camposmdev.client.ui;

import javafx.scene.Node;

public interface View {
    Node getRoot();

    void render();
}
