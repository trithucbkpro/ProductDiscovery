package com.teko.proddiscovery.data.source

import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.source.local.TkProdLocalDataSource
import com.teko.proddiscovery.data.source.remote.TkProdRemoteDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TkProdRepositoryImpl constructor(val localDataSource: TkProdLocalDataSource,
                                       val remoteDataSource: TkProdRemoteDataSource): TkProdRepository {

    override suspend fun getListTkProd(keySearch: String, page: Int, onGetDataLocalComplete:(ResultData<List<TkProduct>>) -> Unit,
                                       onGetDataRemoteComplete:(ResultData<List<TkProduct>>) -> Unit) {
        coroutineScope {
            launch {
                val result = getLocalListTkProd(keySearch, page)
                onGetDataLocalComplete(result)
            }
            launch { val result = getRemoteListTkProd(keySearch, page)
                onGetDataRemoteComplete(result)
            }
        }

        /*return when (val result = getLocalListTkProd(keySearch, page)) {
            is ResultData.Success -> {
                if (result.data.isNotEmpty()) {
                    result
                } else {
                    getRemoteListTkProd(keySearch, page)
                }
            }
            is ResultData.Error -> getRemoteListTkProd(keySearch, page)
            is ResultData.Loading -> result
        }*/
    }

    override suspend fun getTkProd(id: Long): ResultData<TkProduct> {
        return when (val result = getLocalTkProd(id)) {
            is ResultData.Success -> {
                return result
            }
            is ResultData.Error -> getRemoteTkProd(id)
            is ResultData.Loading -> result
        }
    }

    override suspend fun saveTkProd(tkProduct: TkProduct) {
        coroutineScope {
            launch { saveLocalTkProd(tkProduct) }
            launch { saveRemoteTkProd(tkProduct) }
        }
    }

    override suspend fun updateTkProd(tkProduct: TkProduct) {

    }

    override suspend fun getLocalListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>> {
        return localDataSource.getListTkProd(keySearch, page)
    }

    override suspend fun getLocalTkProd(id: Long): ResultData<TkProduct> {
        return localDataSource.getTkProdById(id)
    }

    override suspend fun saveLocalTkProd(tkProduct: TkProduct) {
        localDataSource.saveTkProd(tkProduct)
    }

    override suspend fun updateLocalTkProd(tkProduct: TkProduct) {
        localDataSource.updateTkProd(tkProduct)
    }

    override suspend fun getRemoteListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>> {
        return remoteDataSource.getListTkProd(keySearch, page)
    }

    override suspend fun getRemoteTkProd(id: Long): ResultData<TkProduct> {
        return remoteDataSource.getTkProdById(id)
    }

    override suspend fun saveRemoteTkProd(tkProduct: TkProduct) {
        remoteDataSource.saveTkProd(tkProduct)
    }

    override suspend fun updateRemoteTkProd(tkProduct: TkProduct) {
        remoteDataSource.updateTkProd(tkProduct)
    }
}