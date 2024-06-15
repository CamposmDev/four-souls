package io.github.camposmdev.foursouls.core.util;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Abstract class that represents a WebSocket client on the server side.
 * Provides basic functionality for handling WebSocket messages and connections.
 *
 * @param <T> The type of message type used in decoding messages.
 */
public abstract class AbstractServerWSClient<T> {
	protected final String id;
	protected ServerWebSocket ws;

	/**
	 * Constructs a new AbstractServerWSClient with a unique identifier.
	 *
	 * @param ws the ServerWebSocket instance representing the WebSocket connection.
	 */
	public AbstractServerWSClient(ServerWebSocket ws) {
		this.id = String.valueOf(UUID.randomUUID());
		this.ws = ws;
	}

	/**
	 * Gets the unique identifier for this WebSocket client.
	 *
	 * @return the unique identifier as a String.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Handles binary messages received on the WebSocket connection.
	 *
	 * @param data the binary data received.
	 */
	protected abstract void binaryMessageHandler(Buffer data);

	/**
	 * Handles text messages received on the WebSocket connection.
	 *
	 * @param text the text message received.
	 */
	protected abstract void textMessageHandler(String text);

	/**
	 * Handles the WebSocket connection close event.
	 *
	 * @param arg0 an optional argument representing the reason for closing, can be null.
	 */
	protected abstract void closeHandler(@Nullable Void arg0);

	/**
	 * Decodes a message received on the WebSocket connection.
	 *
	 * @param mtype   the message type.
	 * @param payload the JSON payload associated with the message.
	 */
	protected abstract void decodeMessage(T mtype, JsonObject payload);

	/**
	 * Sends a text message over the WebSocket connection.
	 *
	 * @param text the text message to send.
	 * @return a Future representing the asynchronous send operation.
	 */
	public abstract Future<Void> sendText(String text);
}
