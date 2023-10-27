package org.camposmdev.server;

import org.camposmdev.server.net.ClientRunnable;

import java.io.IOException;
import java.net.ServerSocket;

public class Launcher {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(3000);) {
            System.out.println("Server started");
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
