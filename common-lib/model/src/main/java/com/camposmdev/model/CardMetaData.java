package com.camposmdev.model;

import java.util.Arrays;

public class CardMetaData {
    private String origin; /* URL */
    private String[] images;
    private Card card;

    public CardMetaData(String origin, String[] images) {
        this.origin = origin;
        this.images = images;
    }

    @Override
    public String toString() {
        return origin + "::" + Arrays.toString(images) + "::" + card.toString();
    }
}
