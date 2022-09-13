package app.handlers

import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*

@Location("/get")
class GetHttpBins

fun Application.handlers() {
  install(Locations)
  routing {
     get<GetHttpBins> {
         call.respondText("C")
     }
  }
}
