package by.shvants.features.books.models

import kotlinx.serialization.Serializable

@Serializable
class FetchBooksRequest(val searchQuery: String)