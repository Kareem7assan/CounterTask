package com.rowaad.app.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TweetRepository {
    fun postTweet(text:String): Flow<Response<Any>>

}