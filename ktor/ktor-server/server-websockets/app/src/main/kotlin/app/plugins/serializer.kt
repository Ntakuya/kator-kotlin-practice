package app.plugins

import io.ktor.server.application.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.websocket.*

fun Application.Serializer() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
}
