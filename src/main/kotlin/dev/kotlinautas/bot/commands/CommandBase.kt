package dev.kotlinautas.bot.commands

import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

open class CommandBase(eventHandler: SimpleEventHandler) {
    init {
        eventHandler.onEvent(ChannelMessageEvent::class.java, this::onChannelMessage)
    }

    open fun onChannelMessage(event: ChannelMessageEvent) {}
}