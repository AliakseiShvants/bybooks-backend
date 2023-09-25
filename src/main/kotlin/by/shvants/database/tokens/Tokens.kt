package by.shvants.database.tokens

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    private val tokenId = Tokens.varchar("token_id", 50)
    private val nickName = Tokens.varchar("nickname", 20)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[tokenId] = tokenDTO.id
                it[nickName] = tokenDTO.nickname
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                Tokens.selectAll().toList()
                    .map {
                        TokenDTO(
                            id = it[Tokens.tokenId],
                            nickname = it[Tokens.nickName],
                            token = it[Tokens.token],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
 }