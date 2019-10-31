package com.teko.proddiscovery.data.source.remote

import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.source.TkProdDataSource
import com.teko.proddiscovery.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TkProdRemoteDataSource(
    val tkProdApiService: TkProdApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val networkHandler: NetworkHandler
) : TkProdDataSource {

    override suspend fun getListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>> = withContext(ioDispatcher) {
        if (networkHandler.isConnected) {
            try {
                val response = tkProdApiService.getTkProductList(query = keySearch, page = page)
                if (!response.isSuccessful) {
                    return@withContext ResultData.Error(Exception("Network Exception"))
                }
                if (response.body() == null) {
                    return@withContext ResultData.Error(Exception("No data"))
                } else {
                    return@withContext ResultData.Success(response.body()!!.result.product.map { it.convertToTkProduct() })
                }
            } catch (e: Exception) {
                return@withContext ResultData.Error(Exception("Network Exception"))
            }
        } else {
            return@withContext ResultData.Error(Exception("No network connection"))
        }
    }

    override suspend fun getTkProdById(thcId: Long): ResultData<TkProduct> =
        withContext(ioDispatcher) {
            if (networkHandler.isConnected) {
                try {
                    val response = tkProdApiService.getTkProductById(thcId)
                    if (!response.isSuccessful) {
                        return@withContext ResultData.Error(Exception("Network Exception"))
                    }
                    if (response.body() == null) {
                        return@withContext ResultData.Error(Exception("No data"))
                    } else {
                        return@withContext ResultData.Success(response.body()!!.result.product.convertToTkProduct())
                    }
                } catch (e: Exception) {
                    return@withContext ResultData.Error(Exception("Network Exception"))
                }
            } else {
                return@withContext ResultData.Error(Exception("No network connection"))
            }
        }

    override suspend fun saveTkProd(tkProduct: TkProduct): ResultData<Boolean> =
        withContext(ioDispatcher) {
            return@withContext ResultData.Error(Exception("No data"))
        }

    override suspend fun updateTkProd(tkProduct: TkProduct): ResultData<Boolean> =
        withContext(ioDispatcher) {
            return@withContext ResultData.Error(Exception("No data"))
        }
}