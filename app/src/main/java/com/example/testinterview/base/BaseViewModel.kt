package com.example.testinterview.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun setError(message: String) {
        errorMessage.value = message
    }
}