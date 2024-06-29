package com.rowaad.usecase.tweet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rowaad.MainCoroutineRule
import com.rowaad.app.data.repository.TweetRepository
import com.rowaad.app.usecase.tweet.TweetUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TweetUseCaseTest {

    private lateinit var tweetUseCase: TweetUseCase

    @Mock
    lateinit var tweetRepository: TweetRepository

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        tweetRepository = mock(TweetRepository::class.java)
        tweetUseCase = TweetUseCase(tweetRepository)

    }
    @Test
    fun `tweetCount given spaces text then return zero`() {
        val tweetCountActual = tweetUseCase.tweetCount("  ")
        val expectedCount = 0
        assertEquals(Pair(expectedCount, TweetUseCase.TWEET_CHARACTER_LIMIT - expectedCount), tweetCountActual)
    }


    @Test
    fun `tweetCount given Url then return count23 whatever the url length`(){
        val tweetCountActual = tweetUseCase.tweetCount("https://www.google.com")
        assertEquals(Pair(TweetUseCase.URL_LENGTH,TweetUseCase.TWEET_CHARACTER_LIMIT-TweetUseCase.URL_LENGTH),tweetCountActual)
        val tweetCountActual1 = tweetUseCase.tweetCount("https://developer.x.com/en/docs/counting-characters")
        assertEquals(Pair(23,280-23),tweetCountActual1)
    }

    @Test
    fun `tweetCount given Emoji then return count2`(){
        val tweetCountActual = tweetUseCase.tweetCount("\uD83D\uDC4B")
        assertEquals(Pair(TweetUseCase.EMOJI_LENGTH,TweetUseCase.TWEET_CHARACTER_LIMIT-TweetUseCase.EMOJI_LENGTH),tweetCountActual)
    }
    @Test
    fun `tweetCount given two different Emoji then return count4`(){
        val tweetCountActual = tweetUseCase.tweetCount("\uD83D\uDC4B\uD83D\uDC4B")
        assertEquals(Pair(TweetUseCase.EMOJI_LENGTH * 2,TweetUseCase.TWEET_CHARACTER_LIMIT-(TweetUseCase.EMOJI_LENGTH * 2) ),tweetCountActual)
    }

    @Test
    fun `tweetCount given Emoji sequence using multiple combining then return count2`(){
        val tweetCountActual = tweetUseCase.tweetCount("\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66")
        assertEquals(Pair(TweetUseCase.EMOJI_LENGTH,TweetUseCase.TWEET_CHARACTER_LIMIT-TweetUseCase.EMOJI_LENGTH),tweetCountActual)
    }

    @Test
    fun `tweetCount given Emoji sequence using skin tone combining then return count2`(){
        val tweetCountActual = tweetUseCase.tweetCount("\uD83D\uDC68\u200D\uD83C\uDFA4")
        assertEquals(Pair(TweetUseCase.EMOJI_LENGTH,TweetUseCase.TWEET_CHARACTER_LIMIT-TweetUseCase.EMOJI_LENGTH),tweetCountActual)
    }

    @Test
    fun `tweetCount given normal text then return count length`() {
        val tweetCountActual = tweetUseCase.tweetCount("hello")
        assertEquals(Pair("hello".length, TweetUseCase.TWEET_CHARACTER_LIMIT - "hello".length), tweetCountActual)
    }

    @Test
    fun `tweetCount given cjk text then return double length `() {
        val tweetCountActual = tweetUseCase.tweetCount("这是中文")
        assertEquals(Pair(("这是中文".length)*2, TweetUseCase.TWEET_CHARACTER_LIMIT - ("这是中文".length)*2), tweetCountActual)
    }

    @Test
    fun `tweetCount given mixed text then return countMixed`() {
        val tweetCountActual = tweetUseCase.tweetCount("Hello, 你好世界! 👋")
        val expectedCount = "Hello, ".length + "你好世界".length*2 + "! ".length + TweetUseCase.EMOJI_LENGTH
        assertEquals(Pair(expectedCount, TweetUseCase.TWEET_CHARACTER_LIMIT - expectedCount), tweetCountActual)
    }

    @Test
    fun `tweetCount given over size text then return minus`() {
        val tweetCountActual = tweetUseCase.tweetCount(pargraph)
        val expectedCount = pargraph.length
        assertEquals(Pair(expectedCount, TweetUseCase.TWEET_CHARACTER_LIMIT - expectedCount), tweetCountActual)
    }


}

private val pargraph="Regular Expression, or regex or regexp in short, is extremely and amazingly powerful in searching and manipulating text strings, particularly in processing text files. One line of regex can easily replace several dozen lines of programming codes.\n" +
        "\n" +
        "Regex is supported in all the scripting languages (such as Perl, Python, PHP, and JavaScript); as well as general purpose programming languages such as Java; and even word processors such as Word for searching texts. Getting started with regex may not be easy due to its geeky syntax, but it is certainly worth the investment of your time.\n" +
        "\n"




