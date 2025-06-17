package ca.sheridancollege.midterm.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserPayload(
    @Json(name = "users")
    val users: List<User>
)