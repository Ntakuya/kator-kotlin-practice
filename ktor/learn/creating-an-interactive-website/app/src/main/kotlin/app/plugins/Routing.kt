package app.plugins

import app.models.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktir.server.response.*

fun Application.configureRouting() {
  routing {
    get("/") {
      call.respondRedirect("/articles")
    }

    route("articles") {
      get {
        call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles)))
      }
      get("new") {}
      post {}
      get("{id}") {}
      get("{id}/edit") {}
      post("{id}") {}
    }
  }
}
