package io.github.camposmdev.foursouls.core.util;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractServerWSClient<T> {
	protected final String id;
	protected ServerWebSocket ws;

	public AbstractServerWSClient(ServerWebSocket ws) {
		this.id = String.valueOf(UUID.randomUUID());
		this.ws = ws;
	}

	public String getId() {
		return id;
	}

	protected abstract void binaryMessageHandler(Buffer data);
	protected abstract void textMessageHandler(String text);
	protected abstract void closeHandler(@Nullable Void arg0);
	protected abstract void decodeMessage(T mtype, JsonObject payload);
	public abstract Future<Void> sendText(String text);
}
