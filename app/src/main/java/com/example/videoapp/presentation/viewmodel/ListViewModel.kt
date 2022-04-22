package com.example.videoapp.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.videoapp.data.model.Movie
import com.example.videoapp.data.state.DataState
import com.example.videoapp.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository,
    //val savedStateHandle: SavedStateHandle
) : ViewModel() {


    val movies = repository.getMovieList()
        .cachedIn(viewModelScope)

}




