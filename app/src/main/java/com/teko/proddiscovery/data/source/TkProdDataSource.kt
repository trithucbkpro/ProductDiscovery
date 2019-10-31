package com.teko.proddiscovery.data.source

import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct

interface TkProdDataSource {

    suspend fun getListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>>

    suspend fun getTkProdById(thcId: Long): ResultData<TkProduct>

    suspend fun saveTkProd(tkProduct: TkProduct): ResultData<Boolean>

    suspend fun updateTkProd(tkProduct: TkProduct): ResultData<Boolean>
}