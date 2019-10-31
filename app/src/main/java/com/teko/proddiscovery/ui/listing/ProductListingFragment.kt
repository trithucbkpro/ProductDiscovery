package com.teko.proddiscovery.ui.listing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.teko.proddiscovery.R
import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.ui.base.BaseFragment
import com.teko.proddiscovery.ui.detail.ProductDetailActivity
import com.teko.proddiscovery.utils.ext.obtainViewModel
import com.teko.proddiscovery.utils.view.EndlessScrollListener
import kotlinx.android.synthetic.main.product_listing_fragment.*
import kotlinx.android.synthetic.main.product_listing_fragment.view.*

class ProductListingFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProductListingFragment()
    }

    private lateinit var mViewModel: ProductListingViewModel
    private lateinit var mProductAdapter: ProductListingAdapter
    private var mPage = 1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.product_listing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = obtainViewModel(ProductListingViewModel::class.java).apply {
            errorMsg.observe(this@ProductListingFragment, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })

            mRemoteListProdLiveData.observe(this@ProductListingFragment, Observer { listProduct ->
                listProduct?.let {
                    val listData = listProduct.map { ResultData.Success(it) }
                    mProductAdapter.hideLoadMore()
                    mProductAdapter.setData(listData)
                    mProductAdapter.showLoadMore()
                    mPage++
                }
            })
        }
        mViewModel.getListTkProduct("", mPage)

        initView()
    }

    private fun initView() {
        mProductAdapter = ProductListingAdapter{itemClick->
            if (itemClick is ResultData.Success<TkProduct>) {
                startActivity(Intent(requireContext(), ProductDetailActivity::class.java).apply {
                    putExtra("sku", itemClick.data.sku)
                })
            }

        }
        mRvProduct.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mProductAdapter
            addOnScrollListener(EndlessScrollListener(layoutManager as LinearLayoutManager, object: EndlessScrollListener.OnLoadMoreListener{
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    mViewModel.getListTkProduct("", mPage)
                }
            }))
        }
    }

}
