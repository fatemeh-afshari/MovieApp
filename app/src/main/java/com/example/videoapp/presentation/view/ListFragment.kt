package com.example.videoapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.videoapp.presentation.viewmodel.ListViewModel
import com.example.videoapp.presentation.adapter.MovieListAdapter

import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ListFragment @Inject constructor(
     val adapter: MovieListAdapter
) : Fragment(R.layout.fragment_list) {

     val viewModel: ListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        movieList.adapter = adapter
        movieList.layoutManager = LinearLayoutManager(requireContext())
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                displayProgressBar(true)
            } else {
                displayProgressBar(false)
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    displayError(it.error.message)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
    private fun displayError(message: String?) {
        message?.let {
            listError.visibility = View.VISIBLE
            listError.text = it
        } ?: run {
            listError.visibility = View.GONE
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {

        loadingView.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}