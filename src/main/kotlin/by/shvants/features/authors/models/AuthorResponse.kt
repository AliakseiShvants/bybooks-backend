package by.shvants.features.authors.models

import kotlinx.serialization.Serializable

@Serializable
class AuthorResponse(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val yearOfBirth: Int?,
    val yearOfDeath: Int?,
)