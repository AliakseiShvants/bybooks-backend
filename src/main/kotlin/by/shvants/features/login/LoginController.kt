package by.shvants.features.login

import by.shvants.cache.InMemoryCache
import by.shvants.cache.TokenCache
import by.shvants.database.tokens.TokenDTO
import by.shvants.database.tokens.Tokens
import by.shvants.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(
    private val call: ApplicationCall
) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.nickName)

        when {
            userDTO == null -> call.respond(HttpStatusCode.BadRequest, "User is not found")
            userDTO.password == receive.password -> {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        nickname = receive.nickName,
                        token = token,
                    )
                )

                call.respond(LoginResponseRemote(token))
            }
            else -> call.respond(HttpStatusCode.BadRequest, "Invalid password")
        }
    }
}