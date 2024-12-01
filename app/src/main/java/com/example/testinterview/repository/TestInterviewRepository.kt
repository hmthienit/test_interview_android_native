package com.example.testinterview.repository

import android.content.Context
import com.example.testinterview.repository.api.RemoteTestInterviewResponse
import com.example.testinterview.views.view_sample.models.ResponseSample
import com.google.gson.Gson
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestInterviewRepository
@Inject
constructor(private val context: Context) {
    fun readJsonFromAssets(fileName: String): List<ResponseSample> {
        val jsonString = context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                reader.readText()
            }
        }
        return Gson().fromJson(jsonString, Array<ResponseSample>::class.java).toList()
    }
}