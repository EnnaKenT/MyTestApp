package com.example.mytestapp.presentation.application

import android.app.Application
import com.example.mytestapp.BuildConfig
import com.example.mytestapp.presentation.screens.PostsViewModel
import com.example.mytestapp.data.repository.PostsRepository
import com.example.mytestapp.data.network.api.PostsService
import com.example.mytestapp.domain.usecase.GetPostsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application(), AppBridge {

    override val retrofit: Retrofit by lazy { provideRetrofit() }

    private val appModule = module {
        single { PostsService(retrofit) }
        single<PostsRepository> { PostsRepository.Network(get()) }
        single { GetPostsUseCase(get()) }

        viewModel {
            PostsViewModel(get())
        }
    }

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(appModule)
        }
    }

    private fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}