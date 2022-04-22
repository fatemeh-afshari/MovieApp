package com.example.videoapp.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.videoapp.data.api.API
import com.example.videoapp.data.model.Movie
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.data.state.DataState
import com.example.videoapp.presentation.paging.MoviePagingSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl @Inject constructor(
    val api: API,
    val moviePagingSource: MoviePagingSource
) : Repository {

    override fun getMovieList(): Flow<PagingData<Movie>>  {

          return  Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    moviePagingSource
                }
            ).flow

    }

    override suspend fun getMovieDetail(id: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val response = api.getMovieDetail("$id")
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(DataState.Success(it))
                } ?: emit(DataState.Error(Exception("Error")))
            } else {
                emit(DataState.Error(Exception("Error")))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}