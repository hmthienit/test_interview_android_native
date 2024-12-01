package com.example.testinterview.repository.api

import com.example.testinterview.views.view_sample.models.ResponseSample
import retrofit2.Response
import retrofit2.http.GET

interface RemoteTestInterviewResponse {
    @GET("sample_data_list.json")
    suspend fun getSampleList(): Response<ResponseSample>
}