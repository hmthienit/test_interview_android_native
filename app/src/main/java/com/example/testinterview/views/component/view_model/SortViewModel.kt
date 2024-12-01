package com.example.testinterview.views.component.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testinterview.base.BaseViewModel
import com.example.testinterview.views.component.model.SortOption
import com.example.testinterview.views.component.model.SortType
import com.example.testinterview.views.view_sample.models.ResponseSample
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor() : BaseViewModel() {

    private var _sortOptions = MutableLiveData<List<SortOption>>()
    val sortOptions: MutableLiveData<List<SortOption>> get() = _sortOptions

    init {
        _sortOptions.value = listOf(
            SortOption(1,"Index", SortType.INDEX),
            SortOption(2,"Title", SortType.TITLE),
            SortOption(3,"Date", SortType.DATE)
        )
    }

    fun sortData(sortOption: SortType, dataList: MutableList<ResponseSample>) {
        when (sortOption) {
            SortType.INDEX -> dataList.sortByDescending { it.index }
            SortType.TITLE -> dataList.sortByDescending { it.title }
            SortType.DATE -> dataList.sortByDescending { it.date }
            else -> {}
        }
    }

    fun deselectAllItemsExcept(selectedIndex: Int) {
        _sortOptions.value = _sortOptions.value?.map {
            if (it.index == selectedIndex) it.copy(isSelected = true) else it.copy(isSelected = false)
        }
    }
}