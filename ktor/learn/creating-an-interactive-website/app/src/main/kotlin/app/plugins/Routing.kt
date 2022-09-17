package app.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
       get("/") {
         call.respondText("new Hello World")
       }
       route("/articles") {
         get {
	   call.respondText("articles")
	 }
	 get("new") {
	   call.respondText("Show a page with fields for creating a new article")
	 }
	 post {
	   call.respondText("Create a New Article")
	 }
	 get("{id}") {
	   call.respondText("Get ID")
	 }
	 get("{id}/edit") {
	   call.respondText("Edit")
	 }
	 post("{id}") {
	   call.respondText("Update or delete an article")
	 }
       }
    }
}
