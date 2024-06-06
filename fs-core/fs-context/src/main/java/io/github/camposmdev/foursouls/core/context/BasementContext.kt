package io.github.camposmdev.foursouls.core.context

import com.almasb.fxgl.dsl.getEventBus
import com.almasb.fxgl.event.Subscriber
import io.github.camposmdev.foursouls.core.context.event.BasementEvent
import io.github.camposmdev.foursouls.core.context.store.BasementStore
import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.vertx.core.Vertx
import javafx.event.EventHandler
import javafx.event.EventType

class BasementContext(v: Vertx) : IContext<BasementState, BasementStore>, ISubscribeFXGL<BasementEvent> {
    private val store = BasementStore(v)

    override fun store(): BasementStore {
        return store
    }

    override fun state(): BasementState {
        return store.state()
    }

    override fun subscribeTo(eventType: EventType<BasementEvent>, eventHandler: EventHandler<BasementEvent>): Subscriber {
        return getEventBus().addEventHandler(eventType, eventHandler)
    }

    override fun unsubscribeTo(eventType: EventType<BasementEvent>, eventHandler: EventHandler<BasementEvent>) {
        return getEventBus().removeEventHandler(eventType, eventHandler)
    }
}