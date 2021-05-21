package com.solutionsmax.movieapp.repository

import com.solutionsmax.movieapp.model.MovieListResultEntity
import com.solutionsmax.movieapp.retrofit.ApiInterface
import com.solutionsmax.movieapp.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository
@Inject
constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun retrieveMovieList(
        list_id: Int,
        page: Int,
        apiKey: String
    ): Flow<DataState<MovieListResultEntity>> = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(apiInterface.retrieveMovieList(list_id, page, apiKey)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}