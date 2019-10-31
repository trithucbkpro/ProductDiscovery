package com.teko.proddiscovery.data.source.local

import androidx.room.*
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.entity.TkProduct.Companion.TABLE_NAME

@Dao
interface TkProdDAO {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getListTkProd(): List<TkProduct>

    @Query("SELECT * FROM $TABLE_NAME WHERE sku = :prodId")
    suspend fun getTkProdById(prodId: Long): TkProduct?

    /**
     * return id of new column
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTkProd(prod: TkProduct): Long

    /**
     * return number row updated
     */
    @Update
    suspend fun updateTkProd(prod: TkProduct): Int

    @Query("DELETE FROM $TABLE_NAME WHERE sku = :prodId")
    suspend fun deleteTkProdById(prodId: Long): Int

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAllTkProd(): Int

}