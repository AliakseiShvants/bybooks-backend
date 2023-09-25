package by.shvants.features.books.models

import kotlinx.serialization.Serializable

@Serializable
class BookRequest(
    val authorId: String,
    val title: String,
    val genre: String?,
    val url: String?,
    val year: Int?,
)