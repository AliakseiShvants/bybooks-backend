package by.shvants.features.login

import by.shvants.cache.InMemoryCache
import by.shvants.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            LoginController(call).performLogin()
        }
    }
}