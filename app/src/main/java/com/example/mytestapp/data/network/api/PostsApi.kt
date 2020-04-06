package com.example.mytestapp.data.network.api

import com.example.mytestapp.data.model.GetPostsResponse
import retrofit2.Call
import retrofit2.http.*

interface PostsApi {

    @GET("/api/v1/search_by_date")
    fun getPosts(@Query("tags") tags: String, @Query("page") page: Int): Call<GetPostsResponse>

}