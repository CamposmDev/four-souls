package io.github.camposmdev.foursouls.core.context

import com.almasb.fxgl.event.Subscriber
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType

interface ISubscribeFXGL<T : Event> {
    fun subscribe(eventType: EventType<T>, eventHandler: EventHandler<T>): Subscriber
    fun unsubscribe(eventType: EventType<T>, eventHandler: EventHandler<T>)
}