package by.shvants.database.books

import by.shvants.database.tokens.Tokens
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Books : Table("books") {
    private val bookId = Books.varchar("book_id", 50)
    private val authorId = Books.varchar("author_id", 50)
    private val title = Books.varchar("title", 50)
    private val genre = Books.varchar("genre", 20).nullable()
    private val url = Books.varchar("url", 50).nullable()
    private val year = Books.integer("year").nullable()

    fun insert(bookDTO: BookDTO) {
        transaction {
            Books.insert {
                it[bookId] = bookDTO.id
                it[authorId] = bookDTO.authorId
                it[title] = bookDTO.title
                it[genre] = bookDTO.genre
                it[url] = bookDTO.url
                it[year] = bookDTO.year
            }
        }
    }

    fun fetchAll(): List<BookDTO> {
        return try {
           transaction {
               Books.selectAll().toList()
                   .map {
                       BookDTO(
                           id = it[bookId],
                           authorId = it[authorId],
                           title = it[title],
                           genre = it[genre],
                           url = it[url],
                           year = it[year],
                       )
                   }
           }
        } catch (e: Exception) {
            emptyList()
        }
    }
}