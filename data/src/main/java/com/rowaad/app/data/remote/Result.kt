package com.rowaad.app.data.remote


sealed class NetWorkState {
    data class Success<out T>(val data: T) : NetWorkState()
    data class Error(val th: Throwable) : NetWorkState()
    object Loading : NetWorkState()
    object Idle : NetWorkState()
    object StopLoading: NetWorkState()
    //object Empty: NetWorkState()

}


sealed class TweetState{
    data object Empty :TweetState()
    data class NormalAverage(val count: String,val remain:String) :TweetState()
    data class AboveAverage(val count: String,val remain:String) :TweetState()
    data class OverLimit(val count: String,val remain:String) :TweetState()

}












