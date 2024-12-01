package com.example.testinterview.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.testinterview.R
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingView: View

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createBinding()
        this.binding = binding
        setContentView(binding.root)

        initBaseLoading()
    }

    private fun initBaseLoading() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.loading_layout, null)
        progressBar = loadingView.findViewById(R.id.progress_bar)
        addContentView(
            loadingView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        progressBar.visibility = View.GONE
    }


    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    abstract fun createBinding(): V
}