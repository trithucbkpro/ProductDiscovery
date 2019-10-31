package com.teko.proddiscovery.data.source.remote

import com.teko.proddiscovery.data.source.remote.response.BaseResponse
import com.teko.proddiscovery.data.source.remote.response.EncodeData
import com.teko.proddiscovery.data.source.remote.response.TkProdResponse
import com.teko.proddiscovery.data.source.remote.response.TkProdsResponse
import retrofit2.Response
import retrofit2.http.*

interface TkProdApiService {

    @GET("api/search/")
    suspend fun getTkProductList(@Query("q") query: String = "",
                                 @Query("visitorId") visitorId: String = "",
                                 @Query("channel") channel: String = "pv_online",
                                 @Query("terminal") terminal: String = "CP01",
                                 @Query("_page") page: Int = 1,
                                 @Query("_limit") limit: Int = 10): Response<TkProdsResponse>

    /*@GET("thc")
    suspend fun getTkProdById(@Query("thcId") thcId: Long): Response<TkProdResponse>*/

    @GET("api/products/{product_sku}?channel=pv_showroom&terminal=CP01")
    suspend fun getTkProductById(@Path("product_sku") product_sku: Long): Response<TkProdResponse>

}