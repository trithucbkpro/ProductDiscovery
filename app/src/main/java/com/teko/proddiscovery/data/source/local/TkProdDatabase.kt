package com.teko.proddiscovery.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teko.proddiscovery.data.entity.TkProduct

@Database(entities = [TkProduct::class], version = 1, exportSchema = false)
@TypeConverters(HashMapConverter::class)
abstract class TkProdDatabase : RoomDatabase() {
    abstract fun tkProdDao(): TkProdDAO
}