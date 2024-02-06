package org.camposmdev.server;

import org.camposmdev.server.net.ClientRunnable;

import java.io.IOException;
import java.net.ServerSocket;

public class Launcher {
    public static void main(String[] args) {
        final var PORT = 3000;
        try (ServerSocket ss = new ServerSocket(PORT);) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                var socket = ss.accept();
                ClientRunnable runnable = new ClientRunnable(socket);
                Thread t = new Thread(runnable);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
