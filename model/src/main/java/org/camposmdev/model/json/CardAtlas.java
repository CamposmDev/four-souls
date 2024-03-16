package org.camposmdev.model.json;

import java.util.Map;
import java.util.TreeMap;

public class CardAtlas {
    private Map<String, Character> characterMap;

    public CardAtlas() {
        this.characterMap = new TreeMap<>();
    }

    public void addCharacter(Character sheet) {
        characterMap.put(sheet.getId(), sheet);
    }

    public Map<String, Character> getCharacterMap() {
        return characterMap;
    }

    public void setCharacterMap(Map<String, Character> characterMap) {
        this.characterMap = characterMap;
    }
}
