package com.example.videoapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.videoapp.R
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.data.state.DataState
import com.example.videoapp.databinding.FragmentDetailBinding
import com.example.videoapp.presentation.viewmodel.DetailViewModel
import com.example.videoapp.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment()  {
    private lateinit var dataBinding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var movieId = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movieId = DetailFragmentArgs.fromBundle(it).movieId
        }
        viewModel.getMovieDetail(movieId)
        subscribeObservers()
    }
    private fun subscribeObservers(){
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<MovieDetail> -> {
                    displayProgressBar(false)
                    dataBinding.movie= dataState.data
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
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