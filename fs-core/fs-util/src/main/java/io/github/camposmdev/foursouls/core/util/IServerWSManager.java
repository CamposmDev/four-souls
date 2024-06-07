package io.github.camposmdev.foursouls.core.util;

import io.vertx.core.buffer.Buffer;
import org.jetbrains.annotations.Nullable;

public interface IServerWSManager {
	void binaryMessageHandler(Buffer data);
	void textMessageHandler(String text);
	void closeHandler(@Nullable Void arg0);
	void writeText(String text);
}
