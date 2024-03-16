package org.camposmdev.model.json;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;

import java.util.Map;

public record ImageDataAtlas(
        Map<String, ImageData> images
) {
    public Image source1(String key) {
        return FXGL.image(images.get(key).source1());
    }

    public Image source2(String key) {
        return FXGL.image(images.get(key).source2());
    }
}
