package by.shvants.database.books

import by.shvants.database.authors.AuthorDTO
import by.shvants.features.authors.models.AuthorRequest
import by.shvants.features.authors.models.AuthorResponse
import by.shvants.features.books.models.BookRequest
import by.shvants.features.books.models.BookResponse
import java.util.*

class BookDTO(
    val id: String,
    val authorId: String,
    val title: String,
    val genre: String?,
    val url: String?,
    val year: Int?,
)

fun BookRequest.mapToBookDTO() = BookDTO(
    id = UUID.randomUUID().toString(),
    authorId = authorId,
    title = title,
    genre = genre,
    url = url,
    year = year,
)

fun BookDTO.mapToBookResponse() = BookResponse(
    id = id,
    authorId = authorId,
    title = title,
    genre = genre,
    url = url,
    year = year,
)
