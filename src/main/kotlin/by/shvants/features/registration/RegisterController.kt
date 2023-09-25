package by.shvants.features.registration

import by.shvants.database.tokens.TokenDTO
import by.shvants.database.tokens.Tokens
import by.shvants.database.users.UserDTO
import by.shvants.database.users.Users
import by.shvants.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.time.LocalDateTime
import java.util.UUID

class RegisterController(
    private val call: ApplicationCall
) {

    suspend fun registerNewUser() {
        val receive = call.receive<RegisterReceiveRemote>()

        if (!receive.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val isUserExists = Users.fetchUser(receive.nickName) != null

        when {
            isUserExists -> call.respond(HttpStatusCode.Conflict, "User already exists")
            else -> {
                val token = UUID.randomUUID().toString()

                Users.insert(
                    UserDTO(
                        id = UUID.randomUUID().toString(),
                        nickName = receive.nickName,
                        email = receive.email,
                        password = receive.password,
//                        registrationDate = LocalDateTime.now(),
                    )
                )

                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        nickname = receive.nickName,
                        token = token,
                    )
                )

                call.respond(RegisterResponseRemote(token))
            }
        }
    }
}