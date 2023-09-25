package by.shvants.database.authors

import by.shvants.database.tokens.Tokens
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Authors : Table("authors") {
    private val authorId = Tokens.varchar("author_id", 50)
    private val firstName = Tokens.varchar("first_name", 20).nullable()
    private val lastName = Tokens.varchar("last_name", 20).nullable()
    private val yearOfBirth = Tokens.integer("year_of_birth").nullable()
    private val yearOfDeath = Tokens.integer("year_of_death").nullable()

    fun insert(authorDTO: AuthorDTO) {
        transaction {
            Authors.insert {
                it[authorId] = authorDTO.id
                it[firstName] = authorDTO.firstName
                it[lastName] = authorDTO.lastName
                it[yearOfBirth] = authorDTO.yearOfBirth
                it[yearOfDeath] = authorDTO.yearOfDeath
            }
        }
    }

    fun fetchAll(): List<AuthorDTO> {
        return try {
           transaction {
               Authors.selectAll().toList()
                   .map {
                       AuthorDTO(
                           id = it[authorId],
                           firstName = it[firstName],
                           lastName = it[lastName],
                           yearOfBirth = it[yearOfBirth],
                           yearOfDeath = it[yearOfDeath],
                       )
                   }
           }
        } catch (e: Exception) {
            emptyList<AuthorDTO>()
        }
    }
}