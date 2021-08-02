package dev.kotlinautas.bot.commands

import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import dev.kotlinautas.bot.interfaces.ICommand

class Sociais : ICommand {
    override fun onChannelMessage(event: ChannelMessageEvent, twitchChat: TwitchChat) {
        when (event.message.replace("!", "")) {
            "discord" -> {
                twitchChat.sendMessage(
                    event.channel.name,
                    "Entre na caverna do patocornio (OBS: PARA LIBERAR AS SALAS DA CAVERNA, PASSA NO CANAL DE #⛔REGRAS )-> https://caverna.live/discord"
                )
            }
            "twitter" -> {
                twitchChat.sendMessage(
                    event.channel.name,
                    "https://twitter.com/kotlinautas"
                )
            }
            "github" -> {
            twitchChat.sendMessage(
                    event.channel.name,
                    "https://github.com/kotlinautas"
                )
            }
            "linkedin" -> {
                twitchChat.sendMessage(event.channel.name, "https://www.linkedin.com/company/kotlinautas")
            }
            "regras", "conduta" -> {
                twitchChat.sendMessage(event.channel.name, "Confira o nosso código de conduta para entender melhor sobre a comunidade: https://github.com/Kotlinautas/codigo-de-conduta")
            }
        }
    }
}