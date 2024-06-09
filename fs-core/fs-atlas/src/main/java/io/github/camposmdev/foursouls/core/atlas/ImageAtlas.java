package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.util.assets.CardAsset;

import java.util.Map;

@Deprecated
public record ImageAtlas(
        Map<String, CardAsset> images
) {
    public String source1(String key) {
        return images.get(key).hiResSrc();
    }

    public String source2(String key) {
        return images.get(key).loResSrc();
    }

    public boolean contains(String key) {
        return images.containsKey(key);
    }

    public CardAsset get(String key) {
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
