package com.rowaad.app.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import oauth.signpost.OAuthConsumer
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import oauth.signpost.http.HttpRequest
import java.io.InputStream
import java.net.URI
import java.net.URLEncoder

class OAuthInterceptor(private val consumer: OAuthConsumer) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()

        try {
            // Create a mutable copy of the original request
            val signedRequest = consumer.sign(HttpRequestAdapter(originalRequest))
            val authHeader = signedRequest.getHeader("Authorization")

            // Add the Authorization header to the request builder
            requestBuilder.header("authorization", authHeader)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return chain.proceed(requestBuilder.build())
    }
}

class HttpRequestAdapter(private val request: Request) : HttpRequest {

    private var requestUrl: String = request.url.toString()
    private var method: String = request.method
    private val headers: MutableMap<String, String> = request.headers.toMultimap().mapValues { it.value.joinToString(",") }.toMutableMap()

    override fun getRequestUrl(): String {
        return requestUrl
    }

    override fun setRequestUrl(url: String) {
        requestUrl = url
    }

    override fun getMethod(): String {
        return method
    }


    override fun getHeader(name: String): String? {
        return headers[name]
    }


    override fun setHeader(name: String, value: String) {
        headers[name] = value
    }

    override fun getContentType(): String {
        return headers["Content-Type"] ?: ""
    }

    override fun getMessagePayload(): InputStream? {
        return null
    }

    override fun unwrap(): Any {
        return request
    }

    override fun getAllHeaders(): MutableMap<String, String> {
        return headers
    }

    fun getRequestLine(): String {
        return "$method $requestUrl HTTP/1.1"
    }


}

