package ca.sheridancollege.midterm.repository

import ca.sheridancollege.midterm.data.User
import ca.sheridancollege.midterm.network.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: UserApi
) {
    suspend fun loadUsers(): List<User> = api.getUsers().users
}
