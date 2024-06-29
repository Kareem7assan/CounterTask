package com.rowaad.app.data.remote

import com.rowaad.app.data.model.TweetPost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TweetApi {

    @POST("tweets")
    suspend fun postTweet(
        @Body tweet: TweetPost?=null,
        @Header("Content-Type") contentType: String="application/json"
        ): Response<Any>


}