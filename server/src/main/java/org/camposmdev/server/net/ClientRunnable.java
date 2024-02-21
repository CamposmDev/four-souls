package org.camposmdev.server.net;

import org.camposmdev.model.packet.Packet;
import org.camposmdev.server.model.Client;
import org.camposmdev.server.model.ClientRegistry;
import org.camposmdev.server.model.Player;
import org.camposmdev.server.model.PlayerRegistry;

import java.io.*;
import java.net.Socket;

public class ClientRunnable implements Runnable {
    private Client client;

    public ClientRunnable(Socket socket) {
        try {
            this.client = new Client(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        ClientRegistry.getInstance().add(client);
        try {
            while (true) {
                Packet p = client.recvPacket();
                switch (p.type) {
                    case LOGIN -> handleLogin(p.payload);
                    case PLAYERS -> {
                        System.out.println();
                        System.err.println("Not yet implemented");
                    }
                    default -> client.sendNACKPacket();
                }
            }
        } catch (IOException e) {
            System.out.println("Client " + client + " disconnected");
            ClientRegistry.getInstance().remove(client);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleLogin(Object payload) throws IOException {
        String[] arr = ((String[]) payload);
        String name = arr[0];
        String password = arr[1];
        /* if the client is logged in, send NACK packet */
        if (name.isBlank()) {
            client.sendNACKPacket("Invalid username");
            client.disconnect();
            return;
        }
        final int MIN_LENGTH = 7;
        if (password.length() <= MIN_LENGTH) {
            client.sendNACKPacket("Password must be at least " + MIN_LENGTH + " characters");
            client.disconnect();
            return;
        }
        if (client.isLoggedIn()) {
            System.out.println(client + " already logged in");
            client.sendNACKPacket("You are already logged in");
            client.disconnect();
            return;
        }
        /* check if the given player name is taken by someone else */
        if (ClientRegistry.getInstance().isPlayerTaken(name)) {
            System.out.println(client + " tried to log in as " + name + ", but is already taken");
            client.sendNACKPacket(String.format("'%s' is already taken", name));
            client.disconnect();
            return;
        }
        /* otherwise, try to log in the player */
        if (PlayerRegistry.getInstance().contains(name)) {
            /* check if the password matches */
            var player = PlayerRegistry.getInstance().get(name);
            if (player.getPassword().equals(password)) {
                client.setPlayer(player);
                client.sendACKPacket();
            } else { /* failed to log in */
                client.sendNACKPacket("Password does not match");
                client.disconnect();
            }
        } else {
            /* add new player */
            System.out.println(client + " logged in as " + name);
            var player = new Player(name, password);
            PlayerRegistry.getInstance().add(player);
            client.setPlayer(player);
            client.sendACKPacket();
        }
    }
}
