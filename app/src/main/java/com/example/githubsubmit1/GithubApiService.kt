package com.example.githubsubmit1

import retrofit2.Call
import retrofit2.http.*

interface GithubApiService {
    @GET("search/users")
    fun getUsers(@Query("q") username: String): Call<GithubApiUserResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetailResponse>

    @GET("{username}/follower")
    fun getUserFollower(@Path("username") username: String):Call<UserFollowerResponse>

    @GET("{username}/following")
    fun getUserFollowing(@Path("username") username: String):Call<UserFollowingResponse>
}