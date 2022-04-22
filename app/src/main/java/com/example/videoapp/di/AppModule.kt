package com.example.videoapp.di

import com.example.videoapp.data.api.API
import com.example.videoapp.presentation.paging.MoviePagingSource
import com.example.videoapp.data.repo.Repository
import com.example.videoapp.data.repo.RepositoryImpl
import com.example.videoapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectAPI() : API {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(API::class.java)
    }
    @Singleton
    @Provides
    fun injectPagingSource( api: API ) = MoviePagingSource(api)
    @Singleton
    @Provides
    fun injectNormalRepo( api: API , pagingSource: MoviePagingSource) = RepositoryImpl(api ,pagingSource) as Repository


}
