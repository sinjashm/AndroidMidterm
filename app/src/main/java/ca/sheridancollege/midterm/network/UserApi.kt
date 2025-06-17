package ca.sheridancollege.midterm.network


import ca.sheridancollege.midterm.data.UserPayload
import retrofit2.http.GET

interface UserApi {

    @GET("users.json")
    suspend fun getUsers(): UserPayload

    companion object {
        // JSON lives here
        const val BASE_URL =
            "https://tetervak.dev.fast.sheridanc.on.ca/exam-projects/user-data/data/"

        // images live here
        const val IMAGE_BASE =
            "https://tetervak.dev.fast.sheridanc.on.ca/exam-projects/user-data/images/"
    }
}