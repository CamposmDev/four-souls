package io.github.camposmdev.foursouls.core.api

interface ISignMType<T : Enum<T>> {
    fun sign(mtype: T): String
}