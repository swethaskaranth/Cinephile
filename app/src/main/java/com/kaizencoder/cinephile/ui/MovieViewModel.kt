package com.kaizencoder.cinephile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaizencoder.cinephile.networking.response.Movie
import com.kaizencoder.cinephile.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    val movies : Flow<PagingData<Movie>> = repository.getMovies().cachedIn(viewModelScope)


}