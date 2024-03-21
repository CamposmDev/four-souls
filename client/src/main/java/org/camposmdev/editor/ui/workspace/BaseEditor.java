package org.camposmdev.editor.ui.workspace;

public abstract class BaseEditor implements IEditor {
    private String id, image;

    @Override
    public String id() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }
}
