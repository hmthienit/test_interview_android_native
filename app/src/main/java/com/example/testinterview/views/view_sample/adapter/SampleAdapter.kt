package com.example.testinterview.views.view_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.testinterview.base.BaseAdapter
import com.example.testinterview.databinding.ItemSampleBinding
import com.example.testinterview.views.view_sample.models.ResponseSample

class SampleAdapter : BaseAdapter<ResponseSample, ItemSampleBinding>(ItemDiffCallback()) {

    override fun bind(binding: ItemSampleBinding, item: ResponseSample) {
        binding.info = item
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemSampleBinding {
        return ItemSampleBinding.inflate(inflater, parent, false)
    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<ResponseSample>() {
    override fun areItemsTheSame(oldItem: ResponseSample, newItem: ResponseSample): Boolean {
        return oldItem.index == newItem.index
    }

    override fun areContentsTheSame(oldItem: ResponseSample, newItem: ResponseSample): Boolean {
        return oldItem == newItem
    }
}