package org.camposmdev.client.ui.controllers;

import com.almasb.fxgl.ui.UIController;
import org.camposmdev.client.model.Log;

public abstract class FXController implements UIController {
    @Override
    public void init() {
        /* when does this execute? */
        Log.debug("I'VE FINALLY BEEN EXECUTED");
    }
}
