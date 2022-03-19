package com.example.movies20221.server.moviesrepository

import com.example.movies20221.server.MovieDB

class MoviesRepository {

    private val apikey = "535f69a04c6e18f25a85eb7330e889e7"

    suspend fun getMovies() = MovieDB.retrofit.getTopRated(apikey)

}