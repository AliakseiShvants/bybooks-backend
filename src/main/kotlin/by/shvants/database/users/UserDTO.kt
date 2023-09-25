package by.shvants.database.users

import java.time.LocalDateTime

data class UserDTO(
    val id: String,
    val nickName: String,
    val email: String,
    val password: String,
//    val registrationDate: LocalDateTime,
)
