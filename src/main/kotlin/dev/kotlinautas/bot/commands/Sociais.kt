package dev.kotlinautas.bot.commands

import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import dev.kotlinautas.bot.interfaces.ICommand

class Sociais : ICommand {
    override fun onChannelMessage(event: ChannelMessageEvent, twitchChat: TwitchChat) {
        val message = when (event.message.replace("!", "")) {
            "discord" -> {
                "Entre na caverna do patocornio (OBS: PARA LIBERAR AS SALAS DA CAVERNA, PASSA NO CANAL DE #⛔REGRAS )-> https://caverna.live/discord"
            }
            "twitter" -> {
                "https://twitter.com/kotlinautas"
            }
            "github" -> {
                "https://github.com/kotlinautas"
            }
            "linkedin" -> {
                "https://www.linkedin.com/company/kotlinautas"
            }
            "regras", "conduta" -> {
                "Confira o nosso código de conduta para entender melhor sobre a comunidade: https://github.com/Kotlinautas/codigo-de-conduta"
            }
            "site" -> {
                "https://kotlinautas.dev"
            }
            else -> {
                ""
            }
        }

        if (message.isNotEmpty()) {
            twitchChat.sendMessage(event.channel.name, message)
        }
    }
}
