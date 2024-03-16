package org.camposmdev.client.game;

import com.almasb.fxgl.dsl.FXGL;

public class ErrorRunnable implements Runnable {
    private final String message;

    public ErrorRunnable(String message) {
        this.message = message;
    }
    @Override
    public void run() {
        FXGL.getDialogService().showMessageBox(message, () -> {});
    }
}
