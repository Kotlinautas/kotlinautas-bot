package dev.kotlinautas.bot.commands

import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import dev.kotlinautas.bot.models.Viewer

class MegaSena(eventHandler: SimpleEventHandler) : CommandBase(eventHandler) {
    private val viewers = mutableListOf<String>()

    fun saveViewer(viewer: Viewer) {
        if (viewer.username !in viewers) {
            viewers.add(viewer.username)
        }
    }

    override fun onChannelMessage(event: ChannelMessageEvent) {
//        val viewer = Viewer(event.)

        super.onChannelMessage(event)

        println(event)
//        println(event.user.name)
//        println(event.subscriptionTier)
    }
}