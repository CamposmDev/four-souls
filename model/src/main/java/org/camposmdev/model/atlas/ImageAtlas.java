package org.camposmdev.model.atlas;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;

import java.util.Map;

public record ImageAtlas(
        Map<String, ImageInfo> images
) {
    public Image source1(String key) {
        return FXGL.image(images.get(key).source1());
    }

    public Image source2(String key) {
        return FXGL.image(images.get(key).source2());
    }

    public boolean contains(String key) {
        return images.containsKey(key);
    }

    public ImageInfo get(String key) {
        return images.get(key);
    }

//    public void loadSource1All() {
//        images.values().forEach(x -> FXGL.image(x.source1()));
//    }
//
//    public void loadSource2All() {
//        images.values().forEach(x -> FXGL.image(x.source2()));
//    }
}
