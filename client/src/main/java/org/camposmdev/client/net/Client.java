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
    private boolean isLoggedIn;

    public boolean login(String name, String password) {
        try {
            socket = new Socket("localhost", 3000);
            String[] payload = new String[] {name, password};
            var oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new Packet(PacketType.LOGIN, payload));
            oos.flush();
            var ois = new ObjectInputStream(socket.getInputStream());
            Packet p = (Packet) ois.readObject();
            System.out.println(p);
            if (p.type.equals(PacketType.ACK)) {
                isLoggedIn = true;
            } else {
                isLoggedIn = false;
                socket.close();
                return isLoggedIn;
            }
        } catch (IOException e) {
            System.out.println("failed to connect");
            isLoggedIn = false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isLoggedIn;
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

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }
}
