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
                    case LOGIN -> {
                        /* get the payload and split the args */
                        String[] arr = ((String) p.payload).split(",");
                        if (arr.length < 2) {
                            client.sendNACKPacket("Missing fields");
                            client.disconnect();
                        } else {
                            String name = arr[0];
                            String password = arr[1];
                            /* if the client is logged in, send NACK packet */
                            if (client.isLoggedIn()) {
                                System.out.println(client + " already logged in");
                                client.sendNACKPacket("You are already logged in");
                                client.disconnect();
                            } else {
                                /* otherwise, try to log in the player */
                                if (ClientRegistry.getInstance().isPlayerTaken(name)) {
                                    System.out.println(client + " tried to log in as " + name + ", but is already taken");
                                    client.sendNACKPacket("Someone is already logged in as '" + name + '\'');
                                    client.disconnect();
                                } else {
                                    /* log in as player */
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
                                        System.out.println(client + " logging in as new player " + name);
                                        var player = new Player(name, password);
                                        PlayerRegistry.getInstance().add(player);
                                        client.setPlayer(player);
                                        client.sendACKPacket();
                                    }
                                }
                            }
                        }
                    }
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
}
