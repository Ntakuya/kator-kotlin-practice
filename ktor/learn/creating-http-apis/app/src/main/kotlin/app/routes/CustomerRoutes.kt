package app.routes

import app.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
  route("/customers") {
    get {
      if(customerStorage.isNotEmpty()) {
        call.respond(customerStorage)
      } else {
      	call.respondText("No Customers found", status = HttpStatusCode.OK)
      }
    }
    get("{id?}") {
      val id = call.parameters["id"] ?: return@get call.respondText(
        "Missing ID",
	status = HttpStatusCode.BadRequest
      )
      val customer =
        customerStorage.find { it.id == id } ?: return@get call.respondText(
	  "No Customer with id $id",
	  status = HttpStatusCode.NotFound
	)
      call.respond(customer)
    }
    post {
      val customer = call.receive<Customer>()
      customerStorage.add(customer)
      call.respondText("customer stored correctly", status = HttpStatusCode.Created)
    }
    delete("{id?}") {}
  }
}
