package dev.kotlinautas.bot.commands

import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.google.gson.Gson
import dev.kotlinautas.bot.interfaces.ICommand
import dev.kotlinautas.bot.models.Ticket
import dev.kotlinautas.bot.models.Viewer
import java.io.File
import java.util.*

class MegaSena() : ICommand {
    private val viewers = mutableListOf<Viewer>()
    private val random = Random()
    private val gson = Gson()

    private fun saveViewer(viewer: Viewer, twitchChat: TwitchChat, channel: String) {
        if (!viewers.contains(viewer)) {
            viewers.add(viewer)

            val viewersJson = gson.toJson(viewers)
            val viewersFile = File("viewers.json")

            if (!viewersFile.exists()) viewersFile.createNewFile()

            viewersFile.writeText(viewersJson)

            val message = "${viewer.username} você recebeu ${viewer.subscriber + 1} ticket!"

            twitchChat.sendMessage(channel, message)
        }
    }

    override fun onChannelMessage(event: ChannelMessageEvent, twitchChat: TwitchChat) {
        when (event.message) {
            "!ticket" -> {
                val viewer = Viewer(event.user.name, event.subscriptionTier)

                saveViewer(viewer, twitchChat, event.channel.name)
            }
            "!sortear" -> {
                if (event.channel.name == event.user.name) {
                    val tickets = mutableListOf<Ticket>()

                    viewers.forEach {
                        for (i in 0..it.subscriber) {
                            tickets.add(Ticket(UUID.randomUUID().toString(), it))
                        }
                    }

                    tickets.shuffle()

                    twitchChat.sendMessage(event.channel.name, "${tickets[random.nextInt(tickets.size)].viewer.username} ganhou o sorteio!")
                }
            }
        }
    }
}