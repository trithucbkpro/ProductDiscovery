package com.teko.proddiscovery.data.entity


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultData<out R> {

    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [ResultData] is of type [Success] & holds non-null [Success.data].
 */
val ResultData<*>.succeeded
    get() = this is ResultData.Success && data != null