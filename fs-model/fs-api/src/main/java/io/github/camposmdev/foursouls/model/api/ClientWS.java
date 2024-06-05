package io.github.camposmdev.foursouls.model.api;

import io.vertx.core.buffer.Buffer;
import org.jetbrains.annotations.Nullable;

public interface ClientWS {
	String id();
	void binaryMessageHandler(Buffer data);
	void textMessageHandler(String text);
	void closeHandler(@Nullable Void arg0);
}
