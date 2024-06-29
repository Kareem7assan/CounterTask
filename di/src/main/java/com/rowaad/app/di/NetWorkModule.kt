package com.rowaad.app.di

import android.content.Context
import android.util.Base64
import android.util.Log
import com.google.gson.GsonBuilder
import com.rowaad.app.data.remote.TweetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import oauth.signpost.OAuthConsumer
import oauth.signpost.basic.DefaultOAuthConsumer
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import oauth.signpost.http.HttpRequest
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder
import java.util.Date
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {




    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val consumerKey = BuildConfig.TWITTER_CONSUMER_KEY
        val consumerSecret = BuildConfig.TWITTER_CONSUMER_SECRET
        val token = BuildConfig.TWITTER_TOKEN
        val tokenSecret = BuildConfig.TWITTER_TOKEN_SECRET
         val consumer: OAuthConsumer = DefaultOAuthConsumer(consumerKey, consumerSecret).apply {
            setTokenWithSecret(token, tokenSecret)
        }

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(OAuthInterceptor(consumer))
            .addInterceptor(loggingInterceptor)
            .build()

        return okHttpClient
    }



    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
    }

    private fun generateSignature(
        params: Map<String, String>,
        request: Request,
        consumerSecret: String,
        tokenSecret: String
    ): String {
        val baseString = generateBaseString(params, request)
        val signingKey = "$consumerSecret&$tokenSecret"

        val mac = Mac.getInstance("HmacSHA1")
        mac.init(SecretKeySpec(signingKey.toByteArray(), "HmacSHA1"))
        val signatureBytes = mac.doFinal(baseString.toByteArray())
        return Base64.encodeToString(signatureBytes, Base64.NO_WRAP).trim()
    }

    private fun generateBaseString(params: Map<String, String>, request: Request): String {
        val method = request.method
        val url = request.url.toString().split("?")[0]
        val queryParams = request.url.queryParameterNames.associateWith { request.url.queryParameter(it) ?: "" }
        val allParams = (params + queryParams).toSortedMap()
        val encodedParams = allParams.map { "${it.key.urlEncode()}=${it.value.urlEncode()}" }.joinToString("&")
        return "$method&${url.urlEncode()}&${encodedParams.urlEncode()}"
    }

    private fun String.urlEncode(): String {
        return URLEncoder.encode(this, "UTF-8")
    }

    @Provides
    fun providesBaseUrl(): String {
        return "https://api.twitter.com/2/"
    }
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideTweetApi(retrofit: Retrofit): TweetApi {
        return retrofit.create(TweetApi::class.java)
    }
}
