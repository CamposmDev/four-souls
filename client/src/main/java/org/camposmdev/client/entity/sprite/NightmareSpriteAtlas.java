package org.camposmdev.client.entity.sprite;

import java.util.Map;

public record NightmareSpriteAtlas(
        Map<String, SpriteFrame> bubbles,
        Map<String, SpriteFrame> icons,
        Map<String, SpriteFrame> isaacs,
        SpriteFrame[] floors,
        SpriteFrame light
) {

}
