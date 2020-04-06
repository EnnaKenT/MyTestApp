package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.model.GetPostsResponse
import com.example.mytestapp.data.repository.PostsRepository
import com.example.mytestapp.domain.exception.Failure
import com.example.mytestapp.domain.usecase.base.UseCase
import com.example.mytestapp.presentation.base.functional.Either

class GetPostsUseCase(
    private val postsRepository: PostsRepository
) : UseCase<GetPostsResponse, UseCase.None>() {

    private var page = 0
        get() = field++

    override suspend fun run(params: None): Either<Failure<*>, GetPostsResponse> =
        postsRepository.getPosts(TAGS, page)

    class Params private constructor(val tags: String, val page: Int) {
        companion object {
            fun withParams(tags: String, page: Int): Params = Params(tags, page)
        }
    }

    companion object {
        const val TAGS = "story"
    }

}