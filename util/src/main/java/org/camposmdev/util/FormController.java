package org.camposmdev.util;

public abstract class FormController<T> extends FXController{
    public abstract T submit() throws Exception;
}
