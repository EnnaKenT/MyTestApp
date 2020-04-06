package com.example.mytestapp.domain.repository.base

import com.example.mytestapp.domain.exception.Failure
import com.example.mytestapp.presentation.base.functional.Either
import com.example.mytestapp.presentation.base.functional.Either.Left
import com.example.mytestapp.presentation.base.functional.Either.Right
import retrofit2.Call

interface Repository {

    abstract class BaseNetwork {

        protected fun <T> request(call: Call<T>): Either<Failure<*>, T> {
            return try {
                val response = call.execute()
                when (response.isSuccessful && response.body() != null) {
                    true -> Right(response.body()!!)
                    false -> Left(Failure.serverError(response))
                }
            } catch (e: Throwable) {
                Left(Failure.UnexpectedNetworkError())
            }
        }

    }

}