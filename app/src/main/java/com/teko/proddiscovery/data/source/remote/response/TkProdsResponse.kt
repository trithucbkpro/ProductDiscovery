package com.teko.proddiscovery.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.teko.proddiscovery.data.entity.TkProduct

data class TkProdsResponse(@Expose @SerializedName("result") val result: TkProdsResultData): BaseResponse()

data class TkProdsResultData(@Expose @SerializedName("products") val product: List<TkRemoteProduct>,
                             @Expose @SerializedName("keywords") val keyword: List<String>)
