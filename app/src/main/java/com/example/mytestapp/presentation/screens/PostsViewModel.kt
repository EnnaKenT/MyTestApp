package com.example.mytestapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.data.model.Hits
import com.example.mytestapp.domain.usecase.GetPostsUseCase
import com.example.mytestapp.domain.usecase.base.UseCase
import com.example.mytestapp.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class PostsViewModel(private val getPosts: GetPostsUseCase) : BaseViewModel() {

    private val _posts = MutableLiveData<List<Hits>>()
    private val postsList = mutableListOf<Hits>()

    fun getPosts(): LiveData<List<Hits>> = _posts

    fun refreshData() = viewModelScope.launch {
        val words = getPosts(UseCase.None)
        words.getOrNull()?.let { postsList.addAll(it.hits) }
        _posts.value = postsList
    }

}