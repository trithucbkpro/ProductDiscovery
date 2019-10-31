package com.teko.proddiscovery.utils.ext

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teko.proddiscovery.app.MainApplication
import com.teko.proddiscovery.app.ViewModelFactory


fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory(application as MainApplication)).get(viewModelClass)

fun AppCompatActivity.makeFullScreen() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}