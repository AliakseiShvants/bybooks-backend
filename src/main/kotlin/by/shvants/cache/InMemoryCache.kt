package by.shvants.cache

import by.shvants.features.registration.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String,
)

object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}