package io.github.camposmdev.foursouls.core.ui;

public abstract class FormController<T> extends FXController{
    public abstract T submit() throws Exception;
}
