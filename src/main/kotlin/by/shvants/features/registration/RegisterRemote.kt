package by.shvants.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val nickName: String,
    val password: String,
    val email: String,
)

@JvmInline
@Serializable
value class RegisterResponseRemote(val token: String)