package by.shvants

import by.shvants.features.authors.configureAuthorRouting
import by.shvants.features.books.configureBookRouting
import by.shvants.features.login.configureLoginRouting
import by.shvants.features.registration.configureRegisterRouting
import by.shvants.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database

fun main() {
//    Database.connect(
//        url = "jdbc:postgresql://localhost:5432/bybooks",
//        driver = "org.postgresql.Driver",
//        user = "aliakseishvants",
//        password = "FuckingBack"
//    )
//
//    embeddedServer(CIO, port = 8080, host = "127.0.0.1", module = Application::module)
//        .start(wait = true)

    Database.connect(
        url = "jdbc:postgresql://db/bybooks",
        driver = "org.postgresql.Driver",
        user = "aliakseishvants",
        password = "FuckingBack"
    )

    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureAuthorRouting()
    configureBookRouting()
    configureSerialization()
}
