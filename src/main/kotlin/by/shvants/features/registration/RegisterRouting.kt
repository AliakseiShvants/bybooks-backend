package by.shvants.features.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            RegisterController(call).registerNewUser()
        }
    }
}
