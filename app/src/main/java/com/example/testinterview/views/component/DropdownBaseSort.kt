package com.example.testinterview.views.component

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testinterview.databinding.FragmentDropdownOrderHealthyTipsDialogBinding
import com.example.testinterview.utils.RecyclerViewUtils
import com.example.testinterview.views.component.adapter.SortOptionAdapter
import com.example.testinterview.views.component.model.SortOption
import com.example.testinterview.views.component.view_model.SortViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DropdownBaseSort(
    private var sortViewModel: SortViewModel
) :
    BottomSheetDialogFragment() {

    companion object {
        const val SORT_BOTTOM_SHEET_DIALOG = "SORT-BOTTOM-SHEET-DIALOG"
    }

    private lateinit var binding: FragmentDropdownOrderHealthyTipsDialogBinding
    private lateinit var baseCheckAdapter: SortOptionAdapter
    var onCheckItem: ((SortOption) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDropdownOrderHealthyTipsDialogBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            //this line transparent your dialog background
            (view?.parent as ViewGroup).background =
                ColorDrawable(Color.TRANSPARENT)
            initAdapter()
            click()
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.let {
            val sheet = it as BottomSheetDialog
            sheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter() {
        baseCheckAdapter = SortOptionAdapter(sortViewModel)
        RecyclerViewUtils.initAdapter(
            baseCheckAdapter,
            binding.revBottomSheetDialog,
            LinearLayoutManager.VERTICAL,
            1
        )
        baseCheckAdapter.submitList(sortViewModel.sortOptions.value)
        baseCheckAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun click() {
        baseCheckAdapter.onItemClick = {
            onCheckItem?.invoke(it)
            sortViewModel.sortOptions.value?.forEach {
                it.isSelected = false
            }
            it.isSelected = true
            dismiss()
        }
    }

}