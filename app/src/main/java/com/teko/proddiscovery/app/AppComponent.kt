package com.teko.proddiscovery.app

import com.teko.proddiscovery.data.source.RepositoryModule
import com.teko.proddiscovery.ui.detail.ProductDetailViewModel
import com.teko.proddiscovery.ui.listing.ProductListingViewModel
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    //fun application(): Application
    interface Injectable {
        fun inject(appComponent: AppComponent)
    }

    fun inject(app: MainApplication)
    fun inject(productListingViewModel: ProductListingViewModel)
    fun inject(productDetailViewModel: ProductDetailViewModel)
}