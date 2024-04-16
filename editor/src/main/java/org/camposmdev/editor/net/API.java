package org.camposmdev.editor.net;

import com.almasb.fxgl.dsl.FXGL;
import okhttp3.*;
import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.*;
import org.camposmdev.model.card.extra.OutsideCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.*;
import org.camposmdev.util.Log;

import java.io.IOException;

public class API {
    private static API api;
    private static String host = "localhost";
    private static int port = 3000;
    public static final String base_url = "http://" + host + ":" + port + "/api/";
    public static final String card_url = base_url + "card/";
    private static OkHttpClient client;
    public static API instance() {
        if (api == null) api = new API();
        return api;
    }

    private API() {
        client = new OkHttpClient();
    }

    public MasterCardAtlas fetchMasterCardAtlas() {
        var req = new Request.Builder().url(base_url).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful() && res.body() != null) {
                var data = res.body().bytes();
                return MasterCardAtlas.deserialize(data);
            }
        } catch (IOException ex) {
            Log.fatal("Failed to fetch latest version of master card atlas");
        }
        return null;
    }

    public void createBSoulCard(BonusSoulCard card) {
        final var url = card_url + CardType.BSOUL.key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createCharacterCard(CharacterCard card) {
        final var url = card_url + CardType.CHARACTER.key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createEternalCard(EternalCard card) {
        final var url = card_url + CardType.ETERNAL.key();
        String payload = card.toString();
        var reqBody = RequestBody.create(payload, MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createTreasureCard(TreasureCard card) {
        final var url = card_url + CardType.TREASURE.key();
        String payload = card.toString();
        var reqBody = RequestBody.create(payload, MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createMonsterCard(MonsterCard card) {
        final var url = card_url + CardType.MONSTER.key();
        String payload = card.toString();
        var reqBody = RequestBody.create(payload, MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createLootCard(LootCard card) {
        final var url = card_url + CardType.LOOT.key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createRoomCard(RoomCard card) {
        final var url = card_url + CardType.ROOM.key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }

    public void createOutsideCard(OutsideCard card) {
        final var url = card_url + CardType.OUTSIDE.key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        var req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                FXGL.getNotificationService().pushNotification("Card uploaded to server!");
            } else {
                assert res.body() != null;
                FXGL.getNotificationService().pushNotification(res.body().string());
            }
        } catch (IOException ex) {
            Log.fatal("Failed to post to " + url);
        }
    }
}
