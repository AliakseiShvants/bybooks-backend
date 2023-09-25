package by.shvants.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Test(val test: String)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("hello user!")
        }
    }
}
