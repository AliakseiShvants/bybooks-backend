package by.shvants.utils

import by.shvants.database.tokens.Tokens

object TokenCheck {
    fun isTokenValid(token: String) = Tokens.fetchTokens().firstOrNull {it.token == token } != null
    fun isTokenAdmin(token: String) = token == "9452b269-0968-48a1-a2f2-d255ca562c9c"
}