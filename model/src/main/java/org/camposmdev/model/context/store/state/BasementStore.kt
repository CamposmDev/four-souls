package org.camposmdev.model.context.store.state

import org.camposmdev.model.context.store.IStore

class BasementStore : IStore<BasementState> {
    private var currState = BasementState()

    override fun getState(): BasementState {
        return currState
    }

    override fun setState(state: BasementState) {
        TODO("Not yet implemented")
    }
}