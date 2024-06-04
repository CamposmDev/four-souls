package io.github.camposmdev.foursouls.model.fx;

public abstract class FormController<T> extends FXController{
    public abstract T submit() throws Exception;
}
