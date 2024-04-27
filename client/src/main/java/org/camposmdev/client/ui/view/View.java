package org.camposmdev.client.ui.view;

import javafx.scene.Node;

public interface View {
    void render();
    void dispose();
    Node content();
}
