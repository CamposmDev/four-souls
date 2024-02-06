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
                        String[] arr = ((String[]) p.payload);
                        String name = arr[0];
                        String password = arr[1];
                        /* if the client is logged in, send NACK packet */
                        if (client.isLoggedIn()) {
                            System.out.println(client + " already logged in");
                            client.sendNACKPacket();
                        } else {
                            /* otherwise, try to log in the player */
                            if (ClientRegistry.getInstance().isPlayerTaken(name)) {
                                System.out.println(client + " tried to log in as " + name + ", but is already taken");
                                client.sendNACKPacket();
                            } else {
                                /* log in as player */
                                if (PlayerRegistry.getInstance().contains(name)) {
                                    /* check if the password matches */
                                    var player = PlayerRegistry.getInstance().get(name);
                                    if (player.getPassword().equals(password)) {
                                        client.setPlayer(player);
                                        client.sendACKPacket();
                                    } else {
                                        client.sendNACKPacket();
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
