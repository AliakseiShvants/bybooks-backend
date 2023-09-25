package by.shvants.database.authors

import by.shvants.features.authors.models.AuthorRequest
import by.shvants.features.authors.models.AuthorResponse
import java.util.UUID

class AuthorDTO(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val yearOfBirth: Int?,
    val yearOfDeath: Int?,
) {
    override fun toString(): String {
        return "$lastName $firstName"
    }
}

fun AuthorRequest.mapToAuthorDTO() = AuthorDTO(
    id = UUID.randomUUID().toString(),
    firstName = firstName,
    lastName = lastName,
    yearOfBirth = yearOfBirth,
    yearOfDeath = yearOfDeath,
)

fun AuthorDTO.mapToAuthorResponse() = AuthorResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    yearOfBirth = yearOfBirth,
    yearOfDeath = yearOfDeath,
)
