package app

import app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.*
import io.ktor.http.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
  install(CORS) {
    anyHost()
    allowHeader(HttpHeaders.ContentType)
  }
  configureRouting()
}
