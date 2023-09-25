package by.shvants.features.authors

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAuthorRouting() {
    routing {
        post("/authors") {
            val authorsController = AuthorsController(call)
            authorsController.fetchAllAuthors()
        }
        post("/author/add") {
            val authorsController = AuthorsController(call)
            authorsController.addAuthor()
        }

    }
}