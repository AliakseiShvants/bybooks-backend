package by.shvants.features.authors.models

import kotlinx.serialization.Serializable

@Serializable
class FetchAuthorsRequest(val searchQuery: String)