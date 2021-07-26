package dev.kotlinautas.bot.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Viewer (
    val username: String,
    val subscriber: Int,
    val follower: Boolean,
)