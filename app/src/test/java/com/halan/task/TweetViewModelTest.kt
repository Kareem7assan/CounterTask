package com.halan.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.halan.task.viewmodel.TweetViewModel
import com.rowaad.app.data.remote.NetWorkState
import com.rowaad.app.data.remote.TweetState
import com.rowaad.app.usecase.ValidationMsg
import com.rowaad.app.usecase.handleException
import com.rowaad.app.usecase.tweet.TweetUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TweetViewModelTest {
    @Mock
    private lateinit var viewModel: TweetViewModel


    private lateinit var useCase: TweetUseCase


    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()



    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = Mockito.mock(TweetUseCase::class.java)
        viewModel = TweetViewModel(useCase)
    }
    @Test
    fun `validateTweet() when tweet is in range 1 to 260 then emit NormalAverage state`() = runTest {
        val tweet="bad world outside this door"
        val resp=Pair(tweet.length,TweetUseCase.TWEET_CHARACTER_LIMIT - tweet.length)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            assert(awaitItem() is TweetState.Empty)
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.NormalAverage)


        }
    }
    @Test
    fun `validateTweet() when tweet is in range 261 to 280 then emit AboveAverage state`() = runTest {
        val tweet="Regular Expression, or regex or regexp in short, is extremely and amazingly powerful in searching and manipulating text strings, particularly in processing text files. One line of regex can easily replace several dozen lines of programming codes mdasdasdasdkd."
        val resp=Pair(tweet.length,TweetUseCase.TWEET_CHARACTER_LIMIT - tweet.length)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            assert(awaitItem() is TweetState.Empty)
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.AboveAverage)

        }
    }
    @Test
    fun `validateTweet() when tweet is in range 280 then emit AboveAverage state`() = runTest {
        val tweet="Regular Expression, or regex or regexp in short, is extremely and amazingly powerful in searching and manipulating text strings, particularly in processing text files. One line of regex can easily replace several dozen lines of programming codes mdasdasdasdkd mdasdasdasdkd mdasd."
        val resp=Pair(tweet.length,TweetUseCase.TWEET_CHARACTER_LIMIT - tweet.length)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            assert(awaitItem() is TweetState.Empty)
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.AboveAverage)

        }
    }
    @Test
    fun `validateTweet() when tweet is over range 280 then emit OverLimit state`() = runTest {
        val tweet="Regular Expression, or regex or regexp in short, is extremely and amazingly powerful in searching and manipulating text strings, particularly in processing text files. One line of regex can easily replace several dozen lines of programming codes mdasdasdasdkd mdasdasdasdkd mdasd.mdasdasdasdkd mdasdasdasdkd mdasd"
        val resp=Pair(tweet.length,TweetUseCase.TWEET_CHARACTER_LIMIT - tweet.length)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            assert(awaitItem() is TweetState.Empty)
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.OverLimit)

        }
    }
    @Test
    fun `validateTweet() when tweet is empty then emit Empty state`() = runTest {
        val tweet=""
        val resp=Pair(tweet.length,TweetUseCase.TWEET_CHARACTER_LIMIT - tweet.length)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.Empty)

        }
    }

    @Test
    fun `validateTweet() when tweet is double space then emit Empty state`() = runTest {
        val tweet="  "
        val resp=Pair(0,TweetUseCase.TWEET_CHARACTER_LIMIT)
        `when`(useCase.tweetCount(tweet)).thenReturn(resp)

        viewModel.textStateFlow.test {
            viewModel.validateTweet(tweet)
            assert(awaitItem() is  TweetState.Empty)

        }
    }


}



