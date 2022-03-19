package com.example.movies20221.ui.List

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies20221.R
import com.example.movies20221.databinding.CardViewItemMovieBinding
import com.example.movies20221.server.model.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter (
    private val moviesList: ArrayList<Movie>,
    private val onItemClicked: (Movie) -> Unit
): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener { onItemClicked(moviesList[position])}
    }

    fun appendItems(newList: ArrayList<Movie>){
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = moviesList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemMovieBinding.bind(itemView)

        fun bindMovie(movie: Movie){
            with(binding){
                movieTitleTextView.text = movie.title
                releaseDateLabelTextView.text = movie.releaseDate
                voteAverageTextView.text = movie.voteAverage.toString()
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)
            }
        }
    }
}