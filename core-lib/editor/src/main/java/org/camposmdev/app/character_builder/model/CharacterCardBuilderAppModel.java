package org.camposmdev.app.character_builder.model;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.json.ImageData;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;

public class CharacterCardBuilderAppModel {
    private static CharacterCardBuilderAppModel singleton;

    public static CharacterCardBuilderAppModel getInstance() {
        if (singleton == null) singleton = new CharacterCardBuilderAppModel();
        return singleton;
    }

    int ind = 0;
    LinkedList<ImageData> metaCards;

    private static final String SRC = "./character.dat";

    private CharacterCardBuilderAppModel() {
            Path desPath = Path.of(SRC);
            if (desPath.toFile().exists()) {
                loadObject(SRC);
            } else {
                this.metaCards = new LinkedList<>();
            }
    }

    public boolean previous() {
        if (ind <= 0) return false;
        metaCards.get(--ind);
        return true;
    }

    public ImageData peek() {
        return (metaCards.isEmpty()) ? null : metaCards.get(ind);
    }

    public boolean submit(CharacterCard card) {
//        if (ind > metaCards.size()) return true;
//        var m0 = metaCards.get(ind);
//        metaCards.set(ind++, new ImageRecord(m0.getOriginURL(), m0.getImgURL(), ""));
        return false;
    }

    public void save() {
        try {
            var file = new File(SRC);
            var oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this.metaCards);
            oos.flush();
            oos.close();
            System.out.println("Saved object");
            this.metaCards.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean loadObject(String src) {
        try {
            var ois = new ObjectInputStream(new FileInputStream(src));
            this.metaCards = (LinkedList<ImageData>) ois.readObject();
            ois.close();
            System.out.println("Loaded object");
            this.metaCards.forEach(System.out::println);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to load " + src);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
