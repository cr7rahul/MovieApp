package com.solutionsmax.movieapp.model

import com.google.gson.annotations.SerializedName


data class MovieListResultEntity(
    @SerializedName("results")
    var results: List<MovieListItemEntity>
)
