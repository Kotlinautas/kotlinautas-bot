package dev.kotlinautas.bot

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import dev.kotlinautas.bot.commands.MegaSena
import dev.kotlinautas.bot.commands.Sociais
import dev.kotlinautas.bot.interfaces.ICommand
import kotlin.system.exitProcess

object Bot {

    /** Holds the configuration */
    private val configuration: Configuration =
        loadConfiguration()

    /** Holds the client */
    private val twitchClient: TwitchClient = createClient()

    private val commands = mutableListOf<ICommand>()

    /** Register all features */
    fun registerFeatures() {
        twitchClient.chat.eventManager.onEvent(ChannelMessageEvent::class.java) {
                msg ->
                    commands.forEach { it.onChannelMessage(msg, twitchClient.chat) }
        }
    }

    /** Start the bot, connecting it to every channel specified in the configuration */
    fun start() {
        commands.add(MegaSena())
        commands.add(Sociais())

        // Connect to all channels
        for (channel in configuration.channels) {
            twitchClient.chat.joinChannel(channel)
        }
    }

    /** Load the configuration from the config.yaml file */
    private fun loadConfiguration(): Configuration {
        val config: Configuration
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream = classloader.getResourceAsStream("config.yaml")
        val mapper = ObjectMapper(YAMLFactory())

        try {
            config = mapper.readValue(inputStream, Configuration::class.java)
        } catch (ex: Exception) {
            println("Unable to load configuration... Exiting")
            exitProcess(1)
        }

        return config
    }

    /** Create the client */
    private fun createClient(): TwitchClient {
        var clientBuilder = TwitchClientBuilder.builder()
        val client: TwitchClient

        //region Chat related configuration
        val credential = OAuth2Credential(
            "twitch",
            configuration.credentials["irc"]
        )

        clientBuilder = clientBuilder
            .withChatAccount(credential)
            .withEnableChat(true)
        //endregion

        //region Api related configuration
        clientBuilder = clientBuilder
            .withClientId(configuration.api["twitch_client_id"])
            .withClientSecret(configuration.api["twitch_client_secret"])
            .withEnableHelix(true)
            /*
                 * GraphQL has a limited support
                 * Don't expect a bunch of features enabling it
                 */
            .withEnableGraphQL(true)
            /*
                 * Kraken is going to be deprecated
                 * see : https://dev.twitch.tv/docs/v5/#which-api-version-can-you-use
                 * It is only here so you can call methods that are not (yet)
                 * implemented in Helix
                 */
            .withEnableKraken(true)
        //endregion

        // Build the client out of the configured builder
        client = clientBuilder.build()

        return client
    }

}
