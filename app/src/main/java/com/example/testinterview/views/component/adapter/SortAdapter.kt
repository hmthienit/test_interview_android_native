package com.example.testinterview.views.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.testinterview.base.BaseAdapter
import com.example.testinterview.databinding.ItemSortOptionBinding
import com.example.testinterview.views.component.model.SortOption
import com.example.testinterview.views.component.view_model.SortViewModel

class SortOptionAdapter(private val sortViewModel: SortViewModel) : BaseAdapter<SortOption, ItemSortOptionBinding>(SortOptionDiffCallback()) {
    override fun bind(binding: ItemSortOptionBinding, item: SortOption) {
        binding.dataSortOption = item
        binding.executePendingBindings()

        binding.root.setOnClickListener {
            sortViewModel.deselectAllItemsExcept(item.index)
            notifyDataSetChanged()
            onItemClick?.invoke(item)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemSortOptionBinding {
        return ItemSortOptionBinding.inflate(inflater, parent, false)
    }
}

class SortOptionDiffCallback : DiffUtil.ItemCallback<SortOption>() {
    override fun areItemsTheSame(oldItem: SortOption, newItem: SortOption): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SortOption, newItem: SortOption): Boolean {
        return oldItem == newItem
    }
}