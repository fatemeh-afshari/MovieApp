package com.example.videoapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.videoapp.data.model.Movie
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.data.repo.Repository
import com.example.videoapp.data.state.DataState
import com.example.videoapp.presentation.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockRepository: Repository {
    val api = MockAPI()
    val moviePagingSource = MoviePagingSource(api)
    override fun getMovieList(): Flow<PagingData<Movie>> =flow{
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                moviePagingSource
            }
        ).flow
    }

    override suspend fun getMovieDetail(id: Int): Flow<DataState<MovieDetail>> = flow{
        emit(DataState.Loading)
        val result = api.getMovieDetail("1")
        result.body()?.let {
            emit(DataState.Success(it))
        } ?: emit(DataState.Error(Exception("Error")))
    }
}