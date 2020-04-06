package com.example.mytestapp.data.network.api

import com.example.mytestapp.data.model.GetPostsResponse
import retrofit2.Call
import retrofit2.Retrofit

class PostsService constructor(retrofit: Retrofit) : PostsApi {

    private val postApi by lazy { retrofit.create(PostsApi::class.java) }

    override fun getPosts(tags: String, page: Int): Call<GetPostsResponse> =
        postApi.getPosts(tags, page)

}