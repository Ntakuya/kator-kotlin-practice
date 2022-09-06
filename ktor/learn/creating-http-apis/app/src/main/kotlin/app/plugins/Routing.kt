package app.plugins

import app.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
  routing {
    customerRouting()
  }
}
