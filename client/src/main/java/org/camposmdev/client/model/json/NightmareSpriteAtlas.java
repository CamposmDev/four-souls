package org.camposmdev.client.model.json;

import java.util.Map;

public class NightmareSpriteAtlas {
    private Map<String, SpriteFrame> bubbles;
    private Map<String, SpriteFrame> icons;
    private Map<String, SpriteFrame> isaacs;
    private SpriteFrame[] floors;
    private SpriteFrame light;

    public Map<String, SpriteFrame> getBubbles() {
        return bubbles;
    }

    public void setBubbles(Map<String, SpriteFrame> bubbles) {
        this.bubbles = bubbles;
    }

    public Map<String, SpriteFrame> getIcons() {
        return icons;
    }

    public void setIcons(Map<String, SpriteFrame> icons) {
        this.icons = icons;
    }

    public Map<String, SpriteFrame> getIsaacs() {
        return isaacs;
    }

    public void setIsaacs(Map<String, SpriteFrame> isaacs) {
        this.isaacs = isaacs;
    }

    public SpriteFrame getLight() {
        return light;
    }

    public void setLight(SpriteFrame light) {
        this.light = light;
    }

    public SpriteFrame[] getFloors() {
        return floors;
    }

    public void setFloors(SpriteFrame[] floors) {
        this.floors = floors;
    }
}
