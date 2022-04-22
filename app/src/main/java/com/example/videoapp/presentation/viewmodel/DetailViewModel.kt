package com.example.videoapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.data.repo.Repository
import com.example.videoapp.data.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    //val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movie = MutableLiveData<DataState<MovieDetail>>()
    val movieDetail : LiveData<DataState<MovieDetail>>
        get() = movie

    fun getMovieDetail( id:Int) = viewModelScope.launch {
                    repository.getMovieDetail(id)
                        .onEach {dataState ->
                            movie.value = dataState
                        }
                        .launchIn(viewModelScope)

        }


}