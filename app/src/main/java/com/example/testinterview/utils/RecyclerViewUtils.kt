package com.example.testinterview.utils


import android.annotation.SuppressLint
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewUtils {

    @SuppressLint("WrongConstant")
    fun initAdapter(
        mAdapter: RecyclerView.Adapter<*>,
        rev: RecyclerView,
        orientation: Int = LinearLayoutManager.VERTICAL,
        spanCount: Int = 1,
        isSetScrollMiddle: Boolean = true
    ) {
        rev.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, spanCount, orientation, false)
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }

        if (orientation == LinearLayoutManager.HORIZONTAL && isSetScrollMiddle)
            rev.onScrolledToMiddlePosition()

    }

    fun RecyclerView.onScrolledToMiddlePosition() {
        val snapHelper = LinearSnapHelper()
        if (onFlingListener == null)
            snapHelper.attachToRecyclerView(this)
        setOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var view = recyclerView[0]
            }
        })
    }

}