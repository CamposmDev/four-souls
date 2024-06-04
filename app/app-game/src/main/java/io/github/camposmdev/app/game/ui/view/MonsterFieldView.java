package io.github.camposmdev.app.game.ui.view;

import com.almasb.fxgl.texture.Texture;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class MonsterFieldView implements View {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final int MARGIN = 4;
    private final ScrollPane root;
    private final GridPane gridPane;

    public MonsterFieldView() {
        final var SPACING = 4;
        gridPane = new GridPane(SPACING, SPACING);
        gridPane.setPrefSize(WIDTH, HEIGHT);
        root = new ScrollPane(gridPane);
        root.setId("card-scroll-pane");
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void add(Texture arg0) {
        
    }

    public void remove(Texture arg0) {

    }

    @Override
    public void render() {    
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    @Override
    public Node content() {
        return root;
    }
    
}
