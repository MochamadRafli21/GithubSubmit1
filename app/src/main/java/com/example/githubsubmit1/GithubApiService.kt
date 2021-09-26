package com.example.githubsubmit1

import retrofit2.Call
import retrofit2.http.*

interface GithubApiService {
    @GET("search/users")
    fun getUsers(@Query("q") username: String): Call<GithubApiUserResponse>
}