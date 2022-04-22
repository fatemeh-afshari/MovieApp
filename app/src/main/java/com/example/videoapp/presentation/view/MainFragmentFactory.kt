package com.example.videoapp.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.videoapp.presentation.adapter.MovieListAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory @Inject constructor(
     val movieListAdapter: MovieListAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ListFragment::class.java.name -> ListFragment(movieListAdapter)
            DetailFragment::class.java.name -> DetailFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}