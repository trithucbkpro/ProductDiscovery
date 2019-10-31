package com.teko.proddiscovery.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(val application: MainApplication) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val t: T = super.create(modelClass)
        if (t is AppComponent.Injectable) {
            (t as AppComponent.Injectable).inject(application.appComponent)
        }

        return t
    }
}