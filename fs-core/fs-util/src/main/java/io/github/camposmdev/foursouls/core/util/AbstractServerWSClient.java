package io.github.camposmdev.foursouls.core.util;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractServerWSClient<T> {
	protected final String id;
	public AbstractServerWSClient() {
		id = String.valueOf(UUID.randomUUID());
	}

	public String getId() {
		return id;
	}

	protected abstract void binaryMessageHandler(Buffer data);
	protected abstract void textMessageHandler(String text);
	protected abstract void closeHandler(@Nullable Void arg0);
	protected abstract void decodeMessage(T mtype, JsonObject payload);
	protected abstract void writeText(String text);
}
