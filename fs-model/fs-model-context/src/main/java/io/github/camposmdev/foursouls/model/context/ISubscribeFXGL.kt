package io.github.camposmdev.foursouls.model.context

import com.almasb.fxgl.event.Subscriber
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType

interface ISubscribeFXGL<T : Event> {
    fun subscribeTo(eventType: EventType<T>, eventHandler: EventHandler<T>): Subscriber
    fun unsubscribeTo(eventType: EventType<T>, eventHandler: EventHandler<T>)
}