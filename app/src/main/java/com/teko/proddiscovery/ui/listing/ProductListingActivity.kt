package com.teko.proddiscovery.ui.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teko.proddiscovery.R
import com.teko.proddiscovery.utils.ext.makeFullScreen

class ProductListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        setContentView(R.layout.product_listing_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductListingFragment.newInstance())
                .commitNow()
        }
    }

}
