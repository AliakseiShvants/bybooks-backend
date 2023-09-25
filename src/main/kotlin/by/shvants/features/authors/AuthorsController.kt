package by.shvants.features.authors

import by.shvants.database.authors.*
import by.shvants.features.authors.models.AuthorRequest
import by.shvants.features.authors.models.FetchAuthorsRequest
import by.shvants.utils.TokenCheck
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AuthorsController(private val call: ApplicationCall) {

    suspend fun fetchAllAuthors() {
        val request = call.receive<FetchAuthorsRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        when {
            TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty()) -> when {
                request.searchQuery.isBlank() -> call.respond(Authors.fetchAll())
                else -> call.respond(Authors.fetchAll().filter { it.toString().contains(request.searchQuery, ignoreCase = true) })
            }
            else -> call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun addAuthor() {
        val token = call.request.headers["Bearer-Authorization"]

        when {
            TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty()) -> {
                val request = call.receive<AuthorRequest>()
                val author = request.mapToAuthorDTO()
                Authors.insert(author)
                call.respond(author.mapToAuthorResponse())
            }
            else -> call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}