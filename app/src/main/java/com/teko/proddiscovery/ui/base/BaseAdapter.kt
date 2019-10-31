package com.teko.proddiscovery.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teko.proddiscovery.R
import com.teko.proddiscovery.data.entity.ResultData

abstract class BaseAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val VIEW_TYPE_DATA = 1
        const val VIEW_TYPE_LOADING = 0
    }

    protected val listData = mutableListOf<ResultData<T>>()

    abstract fun setData(data: List<ResultData<T>>)

    abstract fun createDataViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int =
        if(listData[position] is ResultData.Loading)
            VIEW_TYPE_LOADING
        else VIEW_TYPE_DATA

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(itemView)
            }
            else -> createDataViewHolder(parent, viewType)
        }
    }

    fun showLoadMore() {
        if (listData.size == 0 || listData.last() !is ResultData.Loading) {
            listData.add(ResultData.Loading)
            notifyDataSetChanged()
        }
    }

    fun hideLoadMore() {
        if (listData.size > 0 && listData.last() is ResultData.Loading) {
            listData.removeAll { element ->
                element is ResultData.Loading
            }
            notifyDataSetChanged()
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}