package com.solutionsmax.movieapp.ui

import androidx.lifecycle.*
import com.solutionsmax.movieapp.model.MovieListResultEntity
import com.solutionsmax.movieapp.repository.MovieRepository
import com.solutionsmax.movieapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<MovieListResultEntity>> = MutableLiveData()

    val dataState: LiveData<DataState<MovieListResultEntity>>
        get() = _dataState

    fun retrieveMovieList(movieListState: MovieListState, list_id: Int, page: Int, apiKey: String) {
        viewModelScope.launch {
            when (movieListState) {
                is MovieListState.GetMovieList -> {
                    movieRepository.retrieveMovieList(list_id, page, apiKey)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MovieListState.None -> {

                }
            }
        }
    }
}

sealed class MovieListState {
    object GetMovieList : MovieListState()
    object None : MovieListState()
}