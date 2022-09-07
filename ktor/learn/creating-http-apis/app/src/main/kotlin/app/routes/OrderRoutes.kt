package app.routes

import app.models.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Route.listOrderRoute() {
  get("/orders") {
      call.respond(orderStorage)
  }
}


fun Route.getOrderRoute() {
  get("/orders/{orderId?}") {
    val id = call.parameters["orderId"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
    val order = orderStorage.find { it.orderId == id } ?: return@get call.respondText(
      "Not Found", status = HttpStatusCode.NotFound
    )
    call.respond(order)
  }
}

fun Route.totalizeOrderRoute() {
  get("/orders/{orderId?}/total") {
    val id = call.parameters["orderId"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
    val order = orderStorage.find { it.orderId == id } ?: return@get call.respondText(
      "Not Found", status = HttpStatusCode.NotFound
    )
    val total = order.contents.sumOf { it.price * it.amount }
    call.respond(total)
  }
}
