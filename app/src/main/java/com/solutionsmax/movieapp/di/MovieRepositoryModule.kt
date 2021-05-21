package com.solutionsmax.movieapp.di

import com.solutionsmax.movieapp.repository.MovieRepository
import com.solutionsmax.movieapp.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Singleton
    @Provides
    fun provideMovieLis(apiInterface: ApiInterface): MovieRepository {
        return MovieRepository(apiInterface)
    }
}