package com.example.testinterview.views.view_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testinterview.R
import com.example.testinterview.base.BaseFragment
import com.example.testinterview.databinding.FragmentSampleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleDetailFragment : BaseFragment<FragmentSampleDetailBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSampleDetailBinding =
        FragmentSampleDetailBinding.inflate(layoutInflater, container, false)

}