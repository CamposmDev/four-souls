package io.github.camposmdev.foursouls.model.ui;

public abstract class FormController<T> extends FXController{
    public abstract T submit() throws Exception;
}
