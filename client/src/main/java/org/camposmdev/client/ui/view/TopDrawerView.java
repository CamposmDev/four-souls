package org.camposmdev.client.ui.view;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class TopDrawerView implements View {
    static final double WIDTH = getAppWidth();
    static final double HEIGHT = 100;
    private final VBox root;
    private boolean hidden;

    public TopDrawerView() {
        var bt1 = getUIFactoryService().newButton("Button1");
        var bt2 = getUIFactoryService().newButton("Button2");
        HBox box = new HBox(8, bt1, bt2);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        box.setPrefWidth(WIDTH);
        box.setPrefHeight(HEIGHT);

        double[] vertices = {
                40, 50,     // Top left corner
                260, 50,    // Top right corner
                200, 80,   // Bottom right corner
                100, 80    // Bottom left corner
        };
        var poly = new Polygon(vertices);
        poly.setFill(Color.BLACK);
        poly.setOpacity(0.5);

        root = new VBox(box, poly);
        root.setAlignment(Pos.CENTER);

        poly.setOnMouseClicked(e -> {
            if (hidden)
                animationBuilder().duration(Duration.millis(250))
                        .onFinished(() -> hidden = false)
                        .translate(root)
                        .to(new Point2D(0, 0))
                        .buildAndPlay();
            else
                animationBuilder().duration(Duration.millis(250))
                        .onFinished(() -> hidden = true)
                        .translate(root)
                        .to(new Point2D(0, -HEIGHT))
                        .buildAndPlay();

        });
    }

    @Override
    public void render() {
        addUINode(root, 0, -HEIGHT);
    }

    @Override
    public void dispose() {
        removeUINode(root);
    }
}
