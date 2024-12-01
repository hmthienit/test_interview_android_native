package com.example.testinterview.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.testinterview.utils.ViewUtils.setOnClickView

abstract class BaseAdapter<T, VB : ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseAdapter<T, VB>.BaseViewHolder>(diffCallback) {
    var onItemClick: ((T) -> Unit)? = null
    abstract fun bind(binding: VB, item: T)
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = createBinding(
            LayoutInflater.from(parent.context),
            parent,
            viewType
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        bind(holder.binding, item)
        holder.itemView.setOnClickView { onItemClick?.invoke(item) }
    }

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}