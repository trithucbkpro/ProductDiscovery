package com.teko.proddiscovery.data.source

import android.app.Application
import androidx.room.Room
import com.teko.proddiscovery.data.source.local.TkProdDatabase
import com.teko.proddiscovery.data.source.local.TkProdLocalDataSource
import com.teko.proddiscovery.data.source.remote.TkProdApiService
import com.teko.proddiscovery.data.source.remote.TkProdRemoteDataSource
import com.teko.proddiscovery.utils.NetworkHandler
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): TkProdDatabase{
        return Room.databaseBuilder(application, TkProdDatabase::class.java, "BasePj.db")/*.addMigrations(migration_1_2)*/.build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(database: TkProdDatabase): TkProdLocalDataSource{
        return TkProdLocalDataSource(database.tkProdDao())
    }

    @Provides
    @Singleton
    fun provideThcApiService(retrofit: Retrofit): TkProdApiService{
        return retrofit.create(TkProdApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: TkProdApiService, networkHandler: NetworkHandler): TkProdRemoteDataSource{
        return TkProdRemoteDataSource(apiService, networkHandler = networkHandler)
    }

    @Provides
    @Singleton
    fun provideThcRepository(localDataSource: TkProdLocalDataSource, remoteDataSource: TkProdRemoteDataSource): TkProdRepository{
        return TkProdRepositoryImpl(localDataSource, remoteDataSource)
    }
}