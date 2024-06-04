package io.github.camposmdev.foursouls.model.util.fx;

public abstract class FormController<T> extends FXController{
    public abstract T submit() throws Exception;
}
