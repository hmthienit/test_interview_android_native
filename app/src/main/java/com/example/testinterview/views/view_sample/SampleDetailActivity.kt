package com.example.testinterview.views.view_sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.testinterview.base.BaseActivity
import com.example.testinterview.databinding.ActivitySampleDetailBinding
import com.example.testinterview.utils.ViewUtils.setOnClickView
import com.example.testinterview.views.view_sample.models.ResponseSample
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleDetailActivity : BaseActivity<ActivitySampleDetailBinding>() {

    companion object {
        const val CALLBACK_INDEX = "CALLBACK-INDEX"
    }

    override fun createBinding(): ActivitySampleDetailBinding =
        ActivitySampleDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataItem: ResponseSample? = intent.extras?.getSerializable(SampleListFragment.SAMPLE_DATA) as? ResponseSample
        dataItem?.let { // Use the dataItem object
            binding.sampleData = it
        }

        binding.btDelete.setOnClickView {
            val resultIntent = Intent().apply {
                putExtra(
                    CALLBACK_INDEX,
                    dataItem?.index
                )
            }
            setResult (RESULT_OK, resultIntent)
            finish()
        }

    }
}