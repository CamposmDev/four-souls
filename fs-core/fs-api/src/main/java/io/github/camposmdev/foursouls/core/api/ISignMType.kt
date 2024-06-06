package io.github.camposmdev.foursouls.core.api

import io.github.camposmdev.foursouls.core.api.message.MType

interface ISignMType {
    fun sign(mtype: MType): String
}