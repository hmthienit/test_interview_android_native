package com.example.testinterview.views.view_sample.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.testinterview.base.BaseViewModel
import com.example.testinterview.repository.TestInterviewRepository
import com.example.testinterview.repository.data.Resource
import com.example.testinterview.views.view_sample.models.ResponseSample
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SampleViewModel
@Inject
constructor(private val repository: TestInterviewRepository) :
    BaseViewModel() {

    private val _sampleData = MutableLiveData<MutableList<ResponseSample>>()
    val sampleData: MutableLiveData<MutableList<ResponseSample>> get() = _sampleData

    fun removeItemByIndex(index: Int) {
        _sampleData.value?.let { list ->
            val updatedList =
                list.filter { it.index != index }.toMutableList()
            _sampleData.value = updatedList
        }
    }

    fun loadJsonData(fileName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = repository.readJsonFromAssets(fileName)
            emit(Resource.success(data = data))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }
}