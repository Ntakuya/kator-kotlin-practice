package app.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.dao.*

fun Application.configureDatabase() {
  Database.connect("jdbc:h2:mem:test", driver =  "org.postgresql.Driver", user = "postgres", password = "password")
}
