package io.github.camposmdev.foursouls.app.editor.api;

import com.almasb.fxgl.dsl.FXGL;
import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.context.store.MomStore;
import io.github.camposmdev.foursouls.model.fx.Log;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import javafx.application.Platform;

import java.io.IOException;

public class API {
    public static String issues = "https://github.com/CamposmDev/four-souls/issues";
    private static API api;
    private static String host = "localhost";
    private static Integer port = 8000;
    public static String base_url = String.format("http://%s:%d/deck", host, port);

    private static MomStore mom;

    public static API instance() {
        if (api == null) api = new API();
        return api;
    }

    private API() {
        mom = new MomStore(Vertx.vertx(), host, port);
//        client = new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .build();
    }

    public Future<Void> login(String username, String password) {
        Promise<Void> promise = Promise.promise();
        mom.loginUser(username, password).onComplete(it -> {
           if (it.succeeded())
               promise.complete();
           else promise.fail(it.cause());
        });
        return promise.future();
    }

    public Future<MasterCardAtlas> getAllDecks() {
        Promise<MasterCardAtlas> promise = Promise.promise();
        mom.getAllDecks(true).onSuccess(data -> {
			try {
				var atlas = MasterCardAtlas.deserialize(data);
                promise.complete(atlas);
			} catch (IOException e) {
				promise.fail(e);
			}
		}).onFailure(err -> Log.fatal(err.getMessage()));
        return promise.future();
    }

    public void appendDeck(BaseCard card) {
        final var name = CardType.categoryOf(card.getCardType()).key();
        mom.appendDeck(name, card)
                .onSuccess(message -> Platform.runLater(() -> FXGL.getNotificationService().pushNotification(message)))
                .onFailure(err -> Platform.runLater(() -> FXGL.getNotificationService().pushNotification(err.getMessage())));
    }

    public void setHostAndPort(String host, int port) {
        API.host = host;
        API.port = port;
        base_url = String.format("http://%s:%d/api/deck/", host, port);
    }

    public String host() {
        return host;
    }

    public Integer port() {
        return port;
    }

    public Future<Long> ping() {
        return mom.ping();
    }
}
