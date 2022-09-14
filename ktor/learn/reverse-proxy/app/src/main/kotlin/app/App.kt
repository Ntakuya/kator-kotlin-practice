package app

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.
EngineMain.main(args)


fun Application.module() {
    routing {
       get("/") {
         call.respondText("Hello world")
       }
    }

    router("/reverse-proxy") {
        intercept()
    }
           
}
