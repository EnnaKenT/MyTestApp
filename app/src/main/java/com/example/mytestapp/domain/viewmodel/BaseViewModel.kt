package com.example.mytestapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mytestapp.domain.exception.Failure
import com.example.mytestapp.presentation.base.functional.Either

abstract class BaseViewModel : ViewModel() {

    protected fun <R> Either<Failure<*>, R>.handle(handler: (R) -> Unit = {}): Any =
        either({}, handler)

    protected inline fun <reified R : Any> Either<Failure<*>, R>.getOrNull(): R? =
        either({}) { it } as? R

    protected inline fun <reified R : Any> Either<Failure<*>, R>.get(default: R): R =
        getOrNull() ?: default

}