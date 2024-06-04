package io.github.camposmdev.app.miser;

import java.util.List;

public interface Constants {
    /* TODO - Let the user decide where to save the data instead of using {TARGET_DIR} */
    String TARGET_DIR = "./editor/src/main/resources/assets/textures/";
    String CARDS_DIR = TARGET_DIR + "cards/";
    String CHARACTER_DIR = CARDS_DIR + "character/";
    String ETERNAL_DIR = CARDS_DIR + "eternal/";
    String TREASURE_DIR = CARDS_DIR + "treasure/";
    String MONSTER_DIR = CARDS_DIR + "monster/";
    String LOOT_DIR = CARDS_DIR + "loot/";
    String MONEY_DIR = CARDS_DIR + "loot/money/";
    String BSOUL_DIR = CARDS_DIR + "bsoul/";
    String ROOM_DIR = CARDS_DIR + "room/";
    String OUTSIDE_DIR = CARDS_DIR + "outside/";
    String JSON_DIR = "./editor/src/main/resources/assets/json/cards/";

    /**
     * Directories that the program will be using to store image and json files.
     */
    String[] Directories = {
            CARDS_DIR,
            CHARACTER_DIR,
            ETERNAL_DIR,
            TREASURE_DIR,
            MONSTER_DIR,
            LOOT_DIR,
            MONEY_DIR,
            BSOUL_DIR,
            ROOM_DIR,
            OUTSIDE_DIR,
            JSON_DIR
    };

    String BASE_URL = "https://foursouls.com/";

    private static String cardQuery(final String CARD_TYPE) {
        final var CARD_QUERY = "card-search/?identical=no&cardstatus=all&card_type=";
        return BASE_URL + CARD_QUERY + CARD_TYPE;
    }

    String CHARACTERS_URL = cardQuery("character");
    String[] ETERNAL_URLS = {
            cardQuery("peternal"), /* Passive Eternals */
            cardQuery("aeternal"), /* Active Eternals */
            cardQuery("paideternal"), /* Paid Eternals */
            cardQuery("oeternal"), /* One-Time Use Eternals */
            cardQuery("seternal") /* Soul Eternals */
    };
    String[] TREASURE_URLS = {
            cardQuery("ptreasure"),    // Passive Treasure
            cardQuery("atreasure"),    // Active Treasure
            cardQuery("paidtreasure"), // Paid Treasure
            cardQuery("otreasure"),    // One Use Treasure
            cardQuery("streasure")     // Soul Treasure
    };
    String[] MONSTER_URLS = {
            cardQuery("bmonster"),    // Basic Monsters
            cardQuery("cmonster"),    // Cursed Monsters
            cardQuery("hmonster"),    // Holy Monsters
            cardQuery("chamonster"),  // Charmed Mosters
            cardQuery("gevent"),      // Good Events
            cardQuery("bevent"),      // Bad Events
            cardQuery("curse"),       // Curses
            cardQuery("boss"),        // Bosses
            cardQuery("epic")         // Epic Bosses
    };
    String[] LOOT_URLS = {
            cardQuery("cards"),    // Cards
            cardQuery("trinkets"), // Trinkets
            cardQuery("pills"),    // Pills
            cardQuery("runes"),    // Runes
            cardQuery("bombs"),    // Bombs
            cardQuery("butter"),   // Butter Beans
            cardQuery("batteries"),// Batteries
            cardQuery("keys"),     // Keys
            cardQuery("dice"),     // Dice Shards
            cardQuery("sheart"),   // Soul Hearts
            cardQuery("bheart"),   // Black Hearts
            cardQuery("sack"),     // Sacks
            cardQuery("lsoul"),    // Lost Souls
            cardQuery("wildcard")  // Wild Cards
    };
    String[] MONEY_URLS = {
            cardQuery("1c"), // One Pennies
            cardQuery("2c"), // Two Pennies
            cardQuery("3c"), // Three Pennies
            cardQuery("4c"), // Four Pennies
            cardQuery("5c"), // Nickels
            cardQuery("10c") // Dimes
    };
    String BSOUL_URL = cardQuery("bsoul");
    String ROOM_URL = cardQuery("room");
    String OUTSIDE_URL = cardQuery("outside");
    List<String> OUTLIAR_IMGS = List.of("FlipCornerNote.png", "", "", "");
}
