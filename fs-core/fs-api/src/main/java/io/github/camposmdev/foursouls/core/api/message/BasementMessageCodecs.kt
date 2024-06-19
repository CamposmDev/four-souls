package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.message.payload.*

class BasementGreetingCodec : JsonMessageCodec<BasementGreeting>() {
    override fun clazz(): Class<BasementGreeting> {
        return BasementGreeting::class.java
    }
}

class BasementChatCodec : JsonMessageCodec<BasementChat>() {
    override fun clazz(): Class<BasementChat> {
        return BasementChat::class.java
    }
}

class BasementDoneCodec : JsonMessageCodec<BasementDone>() {
    override fun clazz(): Class<BasementDone> {
        return BasementDone::class.java
    }
}

class BasementUsersCodec : JsonMessageCodec<BasementUsers>() {
    override fun clazz(): Class<BasementUsers> {
        return BasementUsers::class.java
    }
}

class BasementClosedCodec : JsonMessageCodec<BasementClosed>() {
    override fun clazz(): Class<BasementClosed> {
        return BasementClosed::class.java
    }
}

class BasementErrorCodec : JsonMessageCodec<BasementError>() {
    override fun clazz(): Class<BasementError> {
        return BasementError::class.java
    }
}

class BasementLeaveCodec : JsonMessageCodec<BasementLeave>() {
    override fun clazz(): Class<BasementLeave> {
        return BasementLeave::class.java
    }
}