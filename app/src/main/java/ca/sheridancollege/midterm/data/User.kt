package ca.sheridancollege.midterm.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val id: String,
    val name: String,
    val gender: String,
    val age: Int,
    val location: String,
    val image: String
)
