package org.camposmdev.client.net;

import org.camposmdev.model.packet.Packet;
import org.camposmdev.model.packet.PacketType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static Client singleton;
    public static Client getInstance() {
        if (singleton == null) singleton = new Client();
        return singleton;
    }
    private Socket socket;

    public boolean login(String name, String password) {
        try {
            socket = new Socket("localhost", 3000);
            String payload = name + ',' + password;
            var oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Packet(PacketType.LOGIN, payload));
            oos.flush();
            var ois = new ObjectInputStream(socket.getInputStream());
            Packet p = (Packet) ois.readObject();
            if (p.type.equals(PacketType.ACK)) {
                System.out.println("logged in");
            } else {
                System.out.println("failed to log in");
                socket.close();
                return false;
            }
        } catch (IOException e) {
            System.out.println("failed to connect");
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public Packet recvPacket() throws IOException, ClassNotFoundException {
        var in = new ObjectInputStream(socket.getInputStream());
        return (Packet) in.readObject();
    }

    public void sendPacket(PacketType type, Object payload) throws IOException {
        Packet p = new Packet(type, payload);
        var out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(p);
        out.flush();
    }
}
