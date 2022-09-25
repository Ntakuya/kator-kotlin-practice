package app

import app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
     Serializer()
     routing {
         get("/") {
	     call.respondText("Server")
	 }
     }
}
