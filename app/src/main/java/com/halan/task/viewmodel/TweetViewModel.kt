package com.halan.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowaad.app.data.remote.NetWorkState
import com.rowaad.app.data.remote.TweetState
import com.rowaad.app.usecase.tweet.TweetUseCase
import com.rowaad.app.usecase.handleException
import com.rowaad.app.usecase.tweet.TweetUseCase.Companion.TWEET_CHARACTER_LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class TweetViewModel @Inject constructor(private val tweetUseCase: TweetUseCase) : ViewModel() {

    private var tweetCharacterTyped = 0
    private var tweetCharacterRemaining = 280

    private val _textState = MutableStateFlow<TweetState>(TweetState.Empty)
    val textStateFlow = _textState.asSharedFlow()

    private val _tweetFlow = MutableStateFlow<NetWorkState>(NetWorkState.Idle)
    val tweetFlow = _tweetFlow.asSharedFlow()

    fun validateTweet(tweet:String){
        tweetCharacterTyped = tweetUseCase.tweetCount(tweet).first
        tweetCharacterRemaining = tweetUseCase.tweetCount(tweet).second
        when{
            tweetCharacterTyped in (1..TWEET_CHARACTER_LIMIT-21) -> {
               _textState.value = TweetState.NormalAverage(count = tweetCharacterTyped.toString(), remain = tweetCharacterRemaining.toString())
           }
            tweetCharacterTyped in (TWEET_CHARACTER_LIMIT-20..TWEET_CHARACTER_LIMIT) -> {
               _textState.value = TweetState.AboveAverage(count = tweetCharacterTyped.toString(), remain = tweetCharacterRemaining.toString())
           }
            tweetCharacterTyped > TWEET_CHARACTER_LIMIT -> {
                _textState.value = TweetState.OverLimit(count = tweetCharacterTyped.toString(), remain = tweetCharacterRemaining.toString())
            }
            tweetCharacterTyped == 0 -> {
                _textState.value = TweetState.Empty

            }
        }

    }

    fun postTweet(tweet:String){
        viewModelScope.launch {
            tweetUseCase.postTweet(tweet)
                .onStart { _tweetFlow.emit(NetWorkState.Loading) }
                .onCompletion { _tweetFlow.emit(NetWorkState.StopLoading) }
                .catch {
                    _tweetFlow.emit(NetWorkState.Error(it.handleException())).also {
                        _tweetFlow.emit(NetWorkState.Idle)
                    }
                }
                .collectLatest {
                    _tweetFlow.emit(NetWorkState.Success(it))
                }

        }
    }

}