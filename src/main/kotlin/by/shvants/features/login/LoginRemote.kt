package by.shvants.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val nickName: String,
    val password: String,
)

@JvmInline
@Serializable
value class LoginResponseRemote(val token: String, )

