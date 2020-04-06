package com.example.mytestapp.data.repository

import com.example.mytestapp.data.model.GetPostsResponse
import com.example.mytestapp.data.network.api.PostsService
import com.example.mytestapp.domain.exception.Failure
import com.example.mytestapp.domain.repository.base.Repository
import com.example.mytestapp.presentation.base.functional.Either

interface PostsRepository : Repository {

    fun getPosts(tags: String, page: Int): Either<Failure<*>, GetPostsResponse>

    class Network(private val postsService: PostsService) : Repository.BaseNetwork(),
        PostsRepository {

        override fun getPosts(tags: String, page: Int): Either<Failure<*>, GetPostsResponse> =
            request(postsService.getPosts(tags, page))

    }

}