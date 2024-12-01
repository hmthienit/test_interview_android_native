package com.example.testinterview.views.view_sample.models

import com.example.testinterview.base.BaseModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseSample(
    @SerializedName("index") val index: Int,
    @SerializedName("title") val title: String? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("description") val description: String? = null
) : BaseModel(index), Serializable