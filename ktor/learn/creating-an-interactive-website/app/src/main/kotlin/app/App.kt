package app

import app.plugin.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
  configureTemplating()
  configureRouting()
}
