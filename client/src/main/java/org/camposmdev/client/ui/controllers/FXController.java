package org.camposmdev.client.ui.controllers;

import com.almasb.fxgl.ui.UIController;

public abstract class FXController implements UIController {
    @Override
    public void init() {
        /* when does this execute? */
        System.out.println(FXController.class);
    }
}
