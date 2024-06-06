package io.github.camposmdev.foursouls.core.context.event

import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.payload.BasementPayload
import javafx.event.Event
import javafx.event.EventType

class BasementEvent(
    eventType: EventType<out Event?>?,
    val payload: BasementPayload? = null
) : Event(eventType) {

    companion object {
        val GREETING: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_GREETING.name)
        val CHAT: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_CHAT.name)
        val DONE: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_DONE.name)
        val USERS: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_USERS.name)
        val CLOSED: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_CLOSED.name)
        val ERROR: EventType<BasementEvent> = EventType(ANY, MType.BASEMENT_ERROR.name)
    }
}
