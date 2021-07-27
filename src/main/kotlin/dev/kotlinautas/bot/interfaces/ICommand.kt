package dev.kotlinautas.bot.interfaces

import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

interface ICommand {
    fun onChannelMessage(event: ChannelMessageEvent, twitchChat: TwitchChat)
}