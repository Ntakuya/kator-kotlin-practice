package app

import app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
  install(CORS) {
    anyHost()
    allowHeader(HttpHeaders.ContentType)
  }
  install(ContentNegotiation) {
        json()
    }
  configureRouting()
  
}
