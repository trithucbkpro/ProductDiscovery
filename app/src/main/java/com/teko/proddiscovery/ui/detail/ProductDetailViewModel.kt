package com.teko.proddiscovery.ui.detail

import androidx.lifecycle.ViewModel
import com.teko.proddiscovery.app.AppComponent
import com.teko.proddiscovery.ui.base.BaseViewModel

class ProductDetailViewModel : BaseViewModel(), AppComponent.Injectable {
    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}
