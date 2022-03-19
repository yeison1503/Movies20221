package com.example.movies20221.ui.List

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies20221.databinding.FragmentListBinding
import com.example.movies20221.server.model.Movie

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listviewModel: ListViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private var moviesList: ArrayList<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listviewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(moviesList, onItemClicked = { onMovieItemClicked(it) })
        listBinding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = moviesAdapter
            setHasFixedSize(false)
        }

        listviewModel.getMovies()

        listviewModel.loadMoviesDone.observe(viewLifecycleOwner) { result ->
            onLoadMoviesDoneSuscribe(result)
        }
    }

    private fun onLoadMoviesDoneSuscribe(moviesList: ArrayList<Movie>?) {
        moviesList?.let { moviesAdapter.appendItems(it) }
    }

    private fun onMovieItemClicked(movie: Movie) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(movie))

    }

}