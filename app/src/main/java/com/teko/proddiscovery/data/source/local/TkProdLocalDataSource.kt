package com.teko.proddiscovery.data.source.local

import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.source.TkProdDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TkProdLocalDataSource(private val tkProdDao: TkProdDAO,
                            private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): TkProdDataSource {

    override suspend fun getListTkProd(keySearch: String, page: Int): ResultData<List<TkProduct>> = withContext(ioDispatcher){
        return@withContext try {
            ResultData.Success(tkProdDao.getListTkProd())
        } catch(e: Exception) {
            ResultData.Error(e)
        }
    }

    override suspend fun getTkProdById(thcId: Long): ResultData<TkProduct> = withContext(ioDispatcher) {
        try {
            val task = tkProdDao.getTkProdById(thcId)
            if (task != null) {
                return@withContext ResultData.Success(task)
            } else {
                return@withContext ResultData.Error(Exception("Task not found!"))
            }
        } catch (e: Exception) {
            return@withContext ResultData.Error(e)
        }
    }

    override suspend fun saveTkProd(tkProduct: TkProduct): ResultData<Boolean>{
        val result = tkProdDao.insertTkProd(tkProduct)
        return ResultData.Success(result > 0)
    }

    override suspend fun updateTkProd(tkProduct: TkProduct): ResultData<Boolean> {
        val result = tkProdDao.updateTkProd(tkProduct)
        return ResultData.Success(result>0)
    }
}