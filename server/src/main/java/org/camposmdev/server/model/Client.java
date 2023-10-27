package com.camposmdev.server.model;

import com.camposmdev.model.packet.Packet;
import com.camposmdev.model.packet.PacketType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Player player;


    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.player = null;
    }

    public Packet recvPacket() throws IOException, ClassNotFoundException {
        return (Packet) in.readObject();
    }

    public void sendPacket(PacketType type, Object payload) throws IOException {
        Packet packet = new Packet(type, payload);
        out.writeObject(packet);
        out.flush();
    }

    public void sendNACKPacket() throws IOException {
        Packet.sendNACKPacket(out);
    }

    public void sendACKPacket() throws IOException {
        Packet.sendACKPacket(out);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLoggedIn() {
        return player != null;
    }
}
