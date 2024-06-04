package io.github.camposmdev.foursouls.model.context.store.state

import io.github.camposmdev.foursouls.model.net.BasementUser

class BasementState {
    var host: Boolean = false
    var users: List<BasementUser> = ArrayList()
}