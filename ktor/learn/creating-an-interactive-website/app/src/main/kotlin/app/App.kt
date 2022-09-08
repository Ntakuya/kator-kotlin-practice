package app

import app.handler.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
  install(FreeMarker)
  configureRouting()
}
