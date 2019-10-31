package com.teko.proddiscovery.utils.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teko.proddiscovery.app.MainApplication
import com.teko.proddiscovery.app.ViewModelFactory

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>, activityObserver: Boolean = true) =
    if (activityObserver) ViewModelProvider(requireActivity(), ViewModelFactory(requireActivity().application as MainApplication)).get(viewModelClass)
    else ViewModelProvider(this, ViewModelFactory(requireActivity().application as MainApplication)).get(viewModelClass)