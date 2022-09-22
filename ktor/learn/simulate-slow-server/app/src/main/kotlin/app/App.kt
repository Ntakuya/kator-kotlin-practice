package app

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.time.*
import java.time.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
    route("/slow-server") {
      intercept(ApplicationCallPipeline.Plugins) {
        delay(Duration.ofSeconds(3L))
      }
      get("") {
        call.respondText("so slow")
      }
    }
  }
}

