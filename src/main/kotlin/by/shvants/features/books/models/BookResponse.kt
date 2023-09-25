package by.shvants.features.books.models

import kotlinx.serialization.Serializable

@Serializable
class BookResponse(
    val id: String,
    val authorId: String,
    val title: String,
    val genre: String?,
    val url: String?,
    val year: Int?,
)