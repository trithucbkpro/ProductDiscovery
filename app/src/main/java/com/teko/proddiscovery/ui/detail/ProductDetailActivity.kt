package com.teko.proddiscovery.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teko.proddiscovery.R

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailFragment.newInstance())
                .commitNow()
        }
    }

}
