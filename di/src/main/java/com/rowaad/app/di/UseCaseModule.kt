package com.rowaad.app.di

import com.rowaad.app.data.repository.TweetRepository
import com.rowaad.app.usecase.tweet.TweetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {




    @Provides
    @ViewModelScoped
    fun providesTweetUseCase(tweetRepository: TweetRepository): TweetUseCase {
        return TweetUseCase(tweetRepository)
    }







}

