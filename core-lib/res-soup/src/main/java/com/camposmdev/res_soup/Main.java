package com.camposmdev.res_soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        downloadMonsterCards();
    }

    public static void downloadMonsterCards() throws IOException {
        final String MONSTER_CARDS_URL = "https://foursouls.com/card-search/?card_type=monster";
        var conn =  Jsoup.connect(MONSTER_CARDS_URL);
        var doc = conn.get();
        /* get the list of cards of the tag with id {cardGrid} */
        var cards = doc.getElementById("cardGrid");
        assert cards != null;
        for (Element elem : cards.getElementsByClass("cardGridCell")) {
            /* Iterate each element in {cards} and handle them in a thread */
            MonsterCardThreadHandler thread = new MonsterCardThreadHandler(elem);
            thread.start();
        }
    }
}
