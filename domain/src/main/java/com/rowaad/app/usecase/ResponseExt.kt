package com.rowaad.app.usecase

import android.util.AndroidException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.transform
import retrofit2.Response
import java.io.IOException
import java.net.BindException
import java.net.PortUnreachableException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

inline fun < T, R> Flow<Response<T>>.transformResponseData(
    crossinline onSuccess: suspend FlowCollector<R>.(T) -> Unit
): Flow<R> {
    return transform {
        when {
            it.isSuccessful && it.body() != null ->  onSuccess(it.body()!!)
            it.code() == 400 -> throw Throwable(Consts.ERROR_API.BAD_REQUEST)
            it.code() == 401 -> throw Throwable(Consts.ERROR_API.UNAUTHRIZED)
            it.code() == 403 -> throw Throwable(Consts.ERROR_API.FORBIDDEN)
            it.code() == 404 -> throw Throwable(Consts.ERROR_API.NOT_FOUND)
            it.code() == 500 -> throw Throwable(Consts.ERROR_API.SERVER_ERROR)
            it.code() == 503 -> throw Throwable(Consts.ERROR_API.MAINTENANCE)
            else -> {
                throw Throwable().handleException()
            }
        }
    }


}




fun Throwable.handleException():Throwable{
    printStackTrace()
    return when (this) {
        is AndroidException, is BindException, is PortUnreachableException, is SocketTimeoutException, is UnknownServiceException, is UnknownHostException, is IOException -> {
            Throwable(Consts.ERROR_API.CONNECTION_ERROR)
        }
        is JsonSyntaxException -> {
            Throwable(Consts.ERROR_API.ERROR_FORMAT)
        }
        else -> {
            this
        }
    }
}
