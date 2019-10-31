package com.teko.proddiscovery.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse(@Expose @SerializedName("code") val code: String = RESPONSE_SUCCESS,
                        @Expose @SerializedName("message") val message: String = ""){
    companion object{
        const val RESPONSE_SUCCESS = "SUCCESS"
        const val RESPONSE_FAIL = 1
    }
}