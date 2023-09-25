package by.shvants.features.authors.models

import kotlinx.serialization.Serializable

@Serializable
class FetchAuthorsResponse(val authors: List<AuthorResponse>)