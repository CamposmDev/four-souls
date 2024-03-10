package org.camposmdev.model.json;

import java.util.Iterator;
import java.util.List;

public record DeckAtlas(
        String character,
        List<String> eternal,
        List<String> treasure,
        List<String> monster,
        List<String> loot,
        List<String> money,
        List<String> bsoul,
        List<String> room
) {

}
