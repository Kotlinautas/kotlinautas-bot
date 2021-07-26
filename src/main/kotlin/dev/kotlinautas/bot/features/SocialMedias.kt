package dev.kotlinautas.bot.features

import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

class SocialMedias(eventHandler: SimpleEventHandler) {
    init {
        eventHandler.onEvent(ChannelMessageEvent::class.java, this::onChannelMessage)
    }

    private fun onChannelMessage(event: ChannelMessageEvent) {
        if (event.message.equals("!sociais")) {
            val message = "twitch.tv/kotlinautas"
            event.twitchChat.sendMessage(event.channel.name, message)
        }
    }
}