package by.shvants.features.books.models

import kotlinx.serialization.Serializable

@Serializable
class FetchBooksResponse(val authors: List<BookResponse>)