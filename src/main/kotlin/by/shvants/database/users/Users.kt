package by.shvants.database.users

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val userId = Users.varchar("user_id", 50)
    private val nickName = Users.varchar("nickname", 20)
    private val email = Users.varchar("email", 20)
    private val password = Users.varchar("password", 20)
    //    private val registrationDate = datetime("registration_date")

    override val primaryKey = PrimaryKey(userId, nickName)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[userId] = userDTO.id
                it[nickName] = userDTO.nickName
                it[email] = userDTO.email
                it[password] = userDTO.password
//                it[registrationDate] = userDTO.registrationDate
            }
        }
    }

    fun fetchUser(nickName: String): UserDTO? {
        return transaction {
            val userModel = Users.select { Users.nickName.eq(nickName) }.singleOrNull()
            userModel?.let {
                UserDTO(
                    id = it[Users.userId],
                    nickName = it[Users.nickName],
                    email = it[Users.email],
                    password = it[Users.password],
//                registrationDate = it[Users.registrationDate],
                )
            }
        }
    }
 }