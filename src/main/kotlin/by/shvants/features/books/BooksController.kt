package by.shvants.features.books

import by.shvants.database.authors.*
import by.shvants.database.books.*
import by.shvants.features.authors.models.AuthorRequest
import by.shvants.features.authors.models.FetchAuthorsRequest
import by.shvants.features.books.models.BookRequest
import by.shvants.features.books.models.FetchBooksRequest
import by.shvants.utils.TokenCheck
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class BooksController(private val call: ApplicationCall) {

    suspend fun fetchAllBooks() {
        val request = call.receive<FetchBooksRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        when {
            TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty()) -> when {
                request.searchQuery.isBlank() -> call.respond(Books.fetchAll())
                else -> call.respond(Books.fetchAll().filter { it.title.contains(request.searchQuery, ignoreCase = true) })
            }
            else -> call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun addBook() {
        val token = call.request.headers["Bearer-Authorization"]

        when {
            TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty()) -> {
                val request = call.receive<BookRequest>()
                val book = request.mapToBookDTO()
                Books.insert(book)
                call.respond(book.mapToBookResponse())
            }
            else -> call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}