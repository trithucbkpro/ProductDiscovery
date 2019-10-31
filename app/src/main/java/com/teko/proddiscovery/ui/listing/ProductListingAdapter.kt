package com.teko.proddiscovery.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teko.proddiscovery.R
import com.teko.proddiscovery.data.entity.ResultData
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_tk_product.view.*
import android.graphics.Paint
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class ProductListingAdapter(val onItemClick:(ResultData<TkProduct>) -> Unit): BaseAdapter<TkProduct>() {

    override fun setData(data: List<ResultData<TkProduct>>) {
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun createDataViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tk_product, parent, false)
        return ProductListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductListingViewHolder) {
            holder.bind((listData[position] as ResultData.Success<TkProduct>).data)
        }
    }

    inner class ProductListingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.mTvProductSupplierPrice.paintFlags = itemView.mTvProductSupplierPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        fun bind(product: TkProduct) {
            itemView.mTvProductName.text = if(product.displayName.isNotBlank()) product.displayName else product.name
            if (product.sellPrice > 0) {
                itemView.mTvProductPrice.visibility = View.VISIBLE
                itemView.mTvProductPrice.text = String.format(Locale.ITALY, "%,d đ", product.sellPrice)
                if (product.supplierSalePrice > product.sellPrice) {
                    val discount =
                        ((product.supplierSalePrice - product.sellPrice) * 100 / product.supplierSalePrice)
                    itemView.mTvProductSupplierPrice.visibility = View.VISIBLE
                    itemView.mTvProductSupplierPrice.text = "${product.supplierSalePrice}"

                    itemView.mTvDiscount.visibility = View.VISIBLE
                    itemView.mTvDiscount.text = String.format(Locale.ITALY, "%,d %", discount)
                } else {
                    itemView.mTvProductSupplierPrice.visibility = View.GONE
                    itemView.mTvDiscount.visibility = View.GONE
                }
            } else {
                itemView.mTvProductPrice.visibility = View.GONE
                itemView.mTvProductSupplierPrice.visibility = View.GONE
                itemView.mTvDiscount.visibility = View.GONE
            }

            val listImage = product.getImageList()
            if (listImage.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(listImage[0])
                    .apply(RequestOptions().placeholder(R.drawable.ic_img_product_default).centerCrop())
                    .into(itemView.mImgProduct)
            } else {
                Glide.with(itemView.context)
                    .load(R.drawable.ic_img_product_default)
                    .apply(RequestOptions().centerCrop())
                    .into(itemView.mImgProduct)
            }

            itemView.setOnClickListener { onItemClick(listData[adapterPosition]) }
        }
    }
}