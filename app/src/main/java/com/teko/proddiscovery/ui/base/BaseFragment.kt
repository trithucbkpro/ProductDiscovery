package com.teko.proddiscovery.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.teko.proddiscovery.BuildConfig
import com.teko.proddiscovery.utils.view.ProgressDialogUtil

open class BaseFragment : Fragment() {

    lateinit var progress: ProgressDialogUtil

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress = ProgressDialogUtil(requireActivity())
    }
    protected fun showProgress() {
        try {
            if (!progress.isShowing) {
                if (BuildConfig.DEBUG) {
                    progress = progress.show(true)
                } else {
                    progress = progress.show(false)
                }
            }
        } catch (e: Throwable) {

        }
    }

    protected fun dismissProgress() {
        try {
            if (progress != null && progress.isShowing) {
                progress.dismiss()
            }
        } catch (e: Throwable) {
        }

    }

}