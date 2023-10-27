package org.camposmdev.model.packet;

import java.io.*;

public class Packet implements Serializable {
    public PacketType type;
    public Object payload;

    public Packet(PacketType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public static void sendACKPacket(ObjectOutputStream out) {
        try {
            out.writeObject(new Packet(PacketType.ACK, null));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void sendNACKPacket(ObjectOutputStream out) {
        try {
            out.writeObject(new Packet(PacketType.NACK, null));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
