package com.teko.proddiscovery.utils.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber


open class EndlessScrollListener(val layoutManager: RecyclerView.LayoutManager,
                                 private val loadMoreListener: OnLoadMoreListener,
        // Sets the starting page index
                                 private val startingPageIndex: Int = 1)
    : RecyclerView.OnScrollListener() {

    interface OnLoadMoreListener {
        fun onLoadMore(page: Int, totalItemsCount: Int)
    }

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 5
    // The current offset index of data you have loaded
    private var currentPage = startingPageIndex
    // The total number of items in the dataset after the last loadAvatar
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to loadAvatar.
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition: Int = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            loadMoreListener.onLoadMore(currentPage, totalItemCount)
            loading = true
            Timber.d("[lastVisibleItemPosition=$lastVisibleItemPosition, totalItemCount=$totalItemCount]")
        }
    }
}