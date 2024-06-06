package io.github.camposmdev.foursouls.app.editor.ui.workspace;

import io.github.camposmdev.foursouls.core.card.attribute.ImageInfo;

public abstract class BaseEditor implements CardEditor {
    private String id;
    private ImageInfo image;

    @Override
    public String id() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ImageInfo image() {
        return image;
    }

    @Override
    public void setImage(ImageInfo image) {
        this.image = image;
    }
}
