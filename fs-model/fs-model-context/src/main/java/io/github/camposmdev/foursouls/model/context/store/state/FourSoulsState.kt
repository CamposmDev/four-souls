package io.github.camposmdev.foursouls.model.context.store.state

import io.github.camposmdev.foursouls.model.context.BasementContext
import io.github.camposmdev.foursouls.model.context.ChestContext
import io.github.camposmdev.foursouls.model.context.MomContext
import io.vertx.core.Vertx

class FourSoulsState(v: Vertx, momHost: String, momPort: Int) {
    var chest = ChestContext(v)
    var basement = BasementContext(v)
    val mom = MomContext(v, momHost, momPort)
}