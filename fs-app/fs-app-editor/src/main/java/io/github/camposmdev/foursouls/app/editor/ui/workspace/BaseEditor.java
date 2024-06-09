package io.github.camposmdev.foursouls.app.editor.ui.workspace;

import io.github.camposmdev.foursouls.core.util.assets.CardAsset;

public abstract class BaseEditor implements CardEditor {
    private String id;
    private CardAsset image;

    @Override
    public String id() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public CardAsset image() {
        return image;
    }

    @Override
    public void setImage(CardAsset image) {
        this.image = image;
    }
}
