package com.teko.proddiscovery.data.source

import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct


interface TkProdRepository {

    suspend fun getListTkProd(keySearch: String, page: Int,
        onGetDataLocalComplete: (ResultData<List<TkProduct>>) -> Unit,
        onGetDataRemoteComplete: (ResultData<List<TkProduct>>) -> Unit
    )

    suspend fun getTkProd(id: Long): ResultData<TkProduct>

    suspend fun saveTkProd(tkProduct: TkProduct)

    suspend fun updateTkProd(tkProduct: TkProduct)

    //Local

    suspend fun getLocalListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>>

    suspend fun getLocalTkProd(id: Long): ResultData<TkProduct>

    suspend fun saveLocalTkProd(tkProduct: TkProduct)

    suspend fun updateLocalTkProd(tkProduct: TkProduct)

    //Remote

    suspend fun getRemoteListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>>

    suspend fun getRemoteTkProd(id: Long): ResultData<TkProduct>

    suspend fun saveRemoteTkProd(tkProduct: TkProduct)

    suspend fun updateRemoteTkProd(tkProduct: TkProduct)

}