package com.teko.proddiscovery.ui.listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teko.proddiscovery.app.AppComponent
import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.source.TkProdRepository
import com.teko.proddiscovery.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProductListingViewModel : BaseViewModel(), AppComponent.Injectable {

    @Inject
    lateinit var mTkProdRepository: TkProdRepository
    val mRemoteListProdLiveData = MutableLiveData<List<TkProduct>>()

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun getListTkProduct(keySearch: String, page: Int) {
        viewModelScope.launch {
            val listRemote = mTkProdRepository.getRemoteListTkProd(keySearch, page)
            Timber.d("$listRemote")
            when (listRemote) {
                is ResultData.Success -> {
                    mRemoteListProdLiveData.postValue(listRemote.data)
                }
                is ResultData.Error -> {
                    mRemoteListProdLiveData.postValue(emptyList())
                    errorMsg.postValue(listRemote.exception.message)
                }
            }
        }
    }
}
