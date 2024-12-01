package com.example.testinterview.views.view_sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testinterview.R
import com.example.testinterview.base.BaseFragment
import com.example.testinterview.constant.AppConstants
import com.example.testinterview.databinding.FragmentSampleListBinding
import com.example.testinterview.repository.data.Status
import com.example.testinterview.utils.RecyclerViewUtils
import com.example.testinterview.views.component.DropdownBaseSort
import com.example.testinterview.views.component.view_model.SortViewModel
import com.example.testinterview.views.view_sample.adapter.SampleAdapter
import com.example.testinterview.views.view_sample.view_model.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleListFragment : BaseFragment<FragmentSampleListBinding>() {
    lateinit var sampleViewModel: SampleViewModel
    lateinit var sortViewModel: SortViewModel
    lateinit var sampleAdapter: SampleAdapter
    private lateinit var startActivityLauncher: ActivityResultLauncher<Intent>

    companion object {
        const val SAMPLE_DATA = "SAMPLE-DATA"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sampleViewModel = ViewModelProvider(requireActivity())[SampleViewModel::class.java]
        sortViewModel = ViewModelProvider(requireActivity())[SortViewModel::class.java]
        sampleAdapter = SampleAdapter()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSampleListBinding = FragmentSampleListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onItemClick()
        initAction()
        updateUI()
        sampleViewModel.loadJsonData(AppConstants.SAMPLE_JSON_NAME).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    sampleViewModel.sampleData.value = it.data as MutableList
                }

                Status.LOADING -> {
                    showLoading()
                }

                Status.ERROR -> {
                    hideLoading()
                }
            }
        }
    }

    private fun initAction() {
        val sortBottomSheet = DropdownBaseSort(sortViewModel)
        binding?.layoutDropdown?.layoutDropdown?.setOnClickListener {
            sortBottomSheet.show(childFragmentManager, DropdownBaseSort.SORT_BOTTOM_SHEET_DIALOG)
        }
        sortBottomSheet.onCheckItem = {
            binding?.layoutDropdown?.tvContent?.text =
                getString(R.string.text_sort_by, it.name)
            sortViewModel.sortData(
                it.sortType,
                sampleViewModel.sampleData.value as MutableList
            )
            sampleAdapter.notifyDataSetChanged()
        }
    }

    private fun onItemClick() {
        sampleAdapter.onItemClick = {

            val intent = Intent(requireContext(), SampleDetailActivity::class.java)
            intent.putExtras(
                bundleOf(
                    SAMPLE_DATA to it
                )
            )
            startActivityLauncher.launch(intent)
        }
    }

    private fun initView() {
        RecyclerViewUtils.initAdapter(
            sampleAdapter,
            binding?.revSampleList as RecyclerView,
            LinearLayoutManager.VERTICAL,
            spanCount = 1
        )
    }

    private fun updateUI() {
        sampleViewModel.sampleData.observe(viewLifecycleOwner) {
            sampleAdapter.submitList(it)
        }

        startActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val resultData: Int? =
                        result.data?.getIntExtra(
                            SampleDetailActivity.CALLBACK_INDEX,
                            -1
                        )
                    // Handle the result data
                    sampleViewModel.removeItemByIndex(resultData ?: -1)
                }
            }
    }

}