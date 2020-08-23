package com.github.test.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class RecyclerScrollListener(private val linearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var visibleThreshold = 10

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!loading) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
            Timber.d(
                "Do items loading: enabled && !loading totalCount: %s, visibleCount: %s, firstVisible: %s, visibleThresold: %s",
                totalItemCount, visibleItemCount, firstVisibleItem, visibleThreshold)
            if (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                Timber.d("Do items loading onLoadMore()")
                loading = true
                onLoadMore()
            }
        }
    }

    fun setVisibleThreshold(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    fun setLoading(loading: Boolean) {
        this.loading = loading
    }

    abstract fun onLoadMore()

}