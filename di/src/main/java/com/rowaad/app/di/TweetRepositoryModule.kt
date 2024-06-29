package com.rowaad.app.di

import com.rowaad.app.data.repository.TweetRepository
import com.rowaad.app.data.repository.TweetRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class  TweetRepositoryModule {
    @Binds
    abstract fun providesTweetRepo(addressRepositoryImp: TweetRepositoryImp): TweetRepository


}