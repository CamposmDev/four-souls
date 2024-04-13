package org.camposmdev.res_soup;

import java.util.List;

public interface Constants {
    String TARGET_DIR = "./editor/src/main/resources/assets/";
    String CARDS_DIR = TARGET_DIR + "textures/cards/";
    String CHARACTER_DIR = CARDS_DIR + "character/";
    String ETERNAL_DIR = CARDS_DIR + "eternal/";
    String TREASURE_DIR = CARDS_DIR + "treasure/";
    String MONSTER_DIR = CARDS_DIR + "monster/";
    String LOOT_DIR = CARDS_DIR + "loot/";
    String MONEY_DIR = CARDS_DIR + LOOT_DIR.replaceAll(CARDS_DIR, "") + "money/";
    String BSOUL_DIR = CARDS_DIR + "bsoul/";
    String ROOM_DIR = CARDS_DIR + "room/";
    String OUTSIDE_DIR = CARDS_DIR + "outside/";

    String JSON_DIR = TARGET_DIR + "json/cards/";

    String[] DIRS = {
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
    String CHARACTERS_URL = "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=character";
    String[] ETERNAL_URLS = {
            /* Passive Eternals */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=peternal",
            /* Active Eternals */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=aeternal",
            /* Paid Eternals */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=paideternal",
            /* One-Time Use Eternal */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=oeternal",
            /* Soul Eternals */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=seternal"
    };
    String[] TREASURE_URLS = {
            /* Passive Treasure */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=ptreasure",
            /* Active Treasure */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=atreasure",
            /* Paid Treasure */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=paidtreasure",
            /* One Use Treasure */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=otreasure",
            /* Soul Treasure */
           "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=streasure"
    };
    String[] MONSTER_URLS = {
            /* Basic Monsters */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=bmonster",
            /* Cursed Monsters */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=cmonster",
            /* Holy Monsters */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=hmonster",
            /* Charmed Mosters */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=chamonster",
            /* Good Events  */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=gevent",
            /* Bad Events */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=bevent",
            /* Curses */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=curse",
            /* Bosses */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=boss",
            /* Epic Bosses */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=epic"
    };
    String[] LOOT_URLS = {
            /* Cards */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=cards",
            /* Trinkets */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=trinkets",
            /* Pills */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=pills",
            /* Runes */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=runes",
            /* Bombs */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=bombs",
            /* Butter Beans */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=butter",
            /* Batteries */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=batteries",
            /* Keys */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=keys",
            /* Dice Shards */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=dice",
            /* Soul Hearts */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=sheart",
            /* Black Hearts */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=bheart",
            /* Sacks */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=sack",
            /* Lost Souls */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=lsoul",
            /* Wild Cards */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=wildcard"
    };
    String[] MONEY_URLS = {
            /* One Pennies */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=1c",
            /* Two Pennies */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=2c",
            /* Three Pennies */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=3c",
            /* Four Pennies */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=4c",
            /* Nickels */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=5c",
            /* Dimes */
            "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=10c"
    };
    String BSOUL_URL = "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=bsoul";
    String ROOM_CARDS_URL = "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=room";
    String OUTSIDE_CARDS_URL = "https://foursouls.com/card-search/?identical=no&cardstatus=all&card_type=outside";

    List<String> OUTLIAR_IMGS = List.of("FlipCornerNote.png", "", "", "");
}
