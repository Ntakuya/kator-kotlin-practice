val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val flyway_version: String by project
val exposed_version: String by project

application {
  mainClass.set("app.AppKt")
}

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.flywaydb.flyway") version "9.3.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("io.ktor:ktor-server-freemarker:$ktor_version")
  implementation("io.ktor:ktor-server-cors:$ktor_version")
  implementation("org.postgresql:postgresql:42.2.14")
  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
}

flyway {
  url = "jdbc:postgresql://localhost:5432/creating_an_interactive_website"
  user = "postgres"
  password = "password"
}
