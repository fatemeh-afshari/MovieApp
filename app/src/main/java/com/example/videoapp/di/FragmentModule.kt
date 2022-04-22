package com.example.videoapp.di

import androidx.fragment.app.FragmentFactory
import com.example.videoapp.presentation.adapter.MovieListAdapter
import com.example.videoapp.presentation.view.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {

    @Singleton
    @Provides
    fun provideAdapter(): MovieListAdapter {
        return MovieListAdapter()
    }
    @Singleton
    @Provides
    fun provideMainFragmentFactory(adapter: MovieListAdapter): FragmentFactory {
        return MainFragmentFactory(adapter)
    }
}