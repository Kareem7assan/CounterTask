package com.rowaad.app.data.remote

import retrofit2.Response
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

    @FormUrlEncoded
    @POST("tweets")
    suspend fun postTweet(
        @Field("text") tweet: String?=null
        //@Header("Authorization") authorization: String="OAuth 1.0"

        ): Response<Any>


}