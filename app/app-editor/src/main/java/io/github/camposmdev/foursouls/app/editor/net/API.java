package io.github.camposmdev.foursouls.app.editor.net;

import com.almasb.fxgl.dsl.FXGL;
import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.util.Timex;
import io.github.camposmdev.foursouls.model.util.fx.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class API {
    public static String issues = "https://github.com/CamposmDev/four-souls/issues";
    private static API api;
    private static String host = "http://localhost";
    private static Integer port = 3000;
    public static String base_url = host + ":" + port + "/api/";
    public static String card_url = base_url + "card/";
    private static OkHttpClient client;
    public static API instance() {
        if (api == null) api = new API();
        return api;
    }

    private API() {
        client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public MasterCardAtlas fetchMasterCardAtlas() {
        var req = new Request.Builder().url(base_url).build();
        try (var res = client.newCall(req).execute()) {
            if (res.isSuccessful() && res.body() != null) {
                var data = res.body().bytes();
                return MasterCardAtlas.deserialize(data);
            }
        } catch (IOException ex) {
            Log.fatal("Failed to fetch latest version of MasterCardAtlas");
        }
        return null;
    }

    public void createCard(BaseCard card) {
        var url = card_url + CardType.categoryOf(card.getCardType()).key();
        var reqBody = RequestBody.create(card.toString(), MediaType.parse("application/json"));
        Request req = new Request.Builder().url(url).post(reqBody).build();
        try (var res = client.newCall(req).execute()) {
            if (res.body() != null)
                FXGL.getNotificationService().pushNotification(res.body().string());
        } catch (IOException ex) {
            FXGL.getNotificationService().pushNotification("Failed to connect to server.");
        }
    }

    public void setHostAndPort(String host, int port) {
        API.host = host;
        API.port = port;
        base_url = API.host + ":" + API.port + "/api/";
        card_url = base_url + "card/";
    }

    public String host() {
        return host;
    }

    public Integer port() {
        return port;
    }

    public Long ping() {
        Timex timex = new Timex();
        timex.start();
        MasterCardAtlas atlas = fetchMasterCardAtlas();
        timex.stop();
        return (atlas == null) ? -1L : timex.toMillis();
    }
}
