package org.camposmdev.editor.ui.workspace;

import org.camposmdev.model.atlas.ImageInfo;

public abstract class BaseEditor implements IEditor {
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
