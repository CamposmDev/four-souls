package io.github.camposmdev.foursouls.core.context.impl

import io.github.camposmdev.foursouls.core.api.message.JsonMessageCodec
import io.github.camposmdev.foursouls.core.context.store.state.BasementState

class BasementStateCodec : JsonMessageCodec<BasementState>() {
    override fun clazz(): Class<BasementState> {
        return BasementState::class.java
    }
}