package com.teko.proddiscovery.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    val errorMsg: MutableLiveData<String> = MutableLiveData()
}