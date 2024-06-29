package com.rowaad.app.usecase.tweet

import com.rowaad.app.data.repository.TweetRepository
import com.rowaad.app.usecase.Consts.EMOJI
import com.rowaad.app.usecase.transformResponseData
import kotlinx.coroutines.flow.Flow
import java.text.Normalizer
import java.util.regex.Pattern
import javax.inject.Inject


class TweetUseCase @Inject constructor(
    private val repository: TweetRepository
) {

    suspend fun postTweet(tweet:String): Flow<Any> {
        return repository.postTweet( tweet)
            .transformResponseData {
                emit(it)
            }
    }


    /*
    * first:Twitter Character Encoding
    Twitter API endpoints only accept UTF-8 encoded text. All other encodings must be converted to UTF-8 before sending the the text to the API.
    Twitter counts the length of a Tweet using the Normalization Form C (NFC) version of the text.
    So,
    * first we normalize the text to NFC form.

    * * 2nd:Glyphs used in CJK (Chinese / Japanese / Korean) languages also count as two characters.  Therefore, a Tweet composed of only CJK text can only have a maximum of 140 of these types of glyphs.

    * * * 3rd:URL length is 23 characters
    *
    *
    * * * * 4th: Emojis are 2 characters even if they are composed of significantly more Unicode code points. Emoji weight is defined by a regular expression in twitter-text that looks for sequences of standard emoji combined with one or more Unicode Zero Width Joiners (U+200D).
    *
    * finally : skip the space when there is only space
    * */

    // This regex to detect url in tweet
    private val urlPattern: Pattern = Pattern.compile(
        "(https?://(www\\.)?|www\\.)[a-zA-Z0-9@:%._+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&/=]*)"
    )



    // This regex is for detecting emoji.
    private val emojiPattern: Pattern = Pattern.compile(EMOJI)/*Pattern.compile(
        "(?:[\\uD83C-\\uDBFF\\uDC00-\\uDFFF][\\u1F3FB-\\u1F3FF]?|\\u200D)+"

    )*/

    // Unicode ranges for CJK characters
    private val cjkPattern = Pattern.compile("[\\p{IsHan}\\p{IsHiragana}\\p{IsKatakana}\\p{IsHangul}]")


    fun tweetCount(input: String): Pair<Int, Int> {
        // Normalize the text to NFC form
        val normalizedText = Normalizer.normalize(input, Normalizer.Form.NFC)
        var remainingText = normalizedText
        var characterCount = 0

        // Trim the input to remove leading and trailing spaces
        val trimmedInput = input.trim()

        // If the trimmed input is empty, return zero counts
        println("trimmedInput: $trimmedInput")
        if (trimmedInput.isEmpty()) {
            return Pair(0, TWEET_CHARACTER_LIMIT)
        }

        // Find and count URLs
        val urlMatcher = urlPattern.matcher(remainingText)
        while (urlMatcher.find()) {
            characterCount += URL_LENGTH
            remainingText = remainingText.replace(urlMatcher.group(), "")
        }


        // Find and count emojis
        val emojiMatcher = emojiPattern.matcher(remainingText)
        while (emojiMatcher.find()) {
            characterCount += EMOJI_LENGTH
            remainingText = remainingText.replace(emojiMatcher.group(), "")
        }

        // Find and count CJK characters
        val cjkMatcher = cjkPattern.matcher(remainingText)
        while (cjkMatcher.find()) {
            characterCount += 2 // Each CJK character counts as 2 characters
            remainingText = remainingText.replace(cjkMatcher.group(), "")
        }


        // Count remaining characters
        characterCount += remainingText.codePointCount(0, remainingText.length)

        val charactersRemaining = TWEET_CHARACTER_LIMIT - characterCount
        return Pair(characterCount, charactersRemaining)
    }

    companion object {
        const val TWEET_CHARACTER_LIMIT = 280
        const val URL_LENGTH =23
        const val EMOJI_LENGTH = 2
    }
    

}





