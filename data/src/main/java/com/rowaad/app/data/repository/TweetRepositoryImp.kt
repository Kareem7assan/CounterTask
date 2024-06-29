package com.rowaad.app.data.repository

import com.rowaad.app.data.remote.TweetApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class TweetRepositoryImp @Inject constructor(
    private val api: TweetApi,
    //private val db: PreferencesGateway,
    //private val baseRepository: BaseRepository,
) : TweetRepository {

    override fun postTweet(text: String): Flow<Response<Any>> {
        return flow {
            emit(api.postTweet(tweet = text))
        }
    }


}
