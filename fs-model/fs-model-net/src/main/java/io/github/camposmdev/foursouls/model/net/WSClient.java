package io.github.camposmdev.foursouls.model.net;

import io.vertx.core.buffer.Buffer;
import org.jetbrains.annotations.Nullable;

public interface WSClient {
	String id();
	void binaryMessageHandler(Buffer data);
	void textMessageHandler(String text);
	void closeHandler(@Nullable Void arg0);
}