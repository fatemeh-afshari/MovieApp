package com.example.videoapp.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.videoapp.data.model.Movie
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.data.state.DataState
import kotlinx.coroutines.flow.Flow


interface Repository {
     fun getMovieList() : Flow<PagingData<Movie>>
    suspend fun  getMovieDetail(id:Int): Flow<DataState<MovieDetail>>
}