package com.example.videoapp.presentation.adapter

import com.example.videoapp.R
import com.example.videoapp.data.model.Movie
import com.example.videoapp.databinding.ItemMovieBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapp.presentation.view.ListFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*

private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
class MovieListAdapter :
    PagingDataAdapter<Movie ,MovieListAdapter.MovieViewHolder>(diffUtil),
    MovieClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = getItem(position)
        holder.view.listener = this
    }

    override fun onMovieClicked(v: View) {
        val id = v.movieId.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.movieId = id
        Navigation.findNavController(v).navigate(action)
    }

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root)
}