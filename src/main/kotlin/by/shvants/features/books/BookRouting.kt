package by.shvants.features.books

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureBookRouting() {
    routing {
        post("/books") {
            val booksController = BooksController(call)
            booksController.fetchAllBooks()
        }
        post("/book/add") {
            val booksController = BooksController(call)
            booksController.addBook()
        }
    }
}