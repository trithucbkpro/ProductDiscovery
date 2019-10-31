package com.teko.proddiscovery.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EncodeData(@Expose @SerializedName("hash") val hash: String,
                       @Expose @SerializedName("data") val data: String)