package com.example.testinterview.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testinterview.R

object ViewUtils {

    fun View.enableView() {
        this.isEnabled = true
        this.alpha = 1F
    }

    fun View.disableView(alpha: Float = 1F) {
        this.isEnabled = false
        this.alpha = alpha
    }

    fun View.showView() {
        visibility = View.VISIBLE
    }

    fun View.hideView(isGone: Boolean = true) {
        visibility = if (isGone) {
            View.GONE
        } else {
            View.INVISIBLE
        }
    }

    fun View.navigateTo(des: Int, bundle: Bundle = bundleOf()) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.anim_in_right).setExitAnim(R.anim.anim_out_left)
            .setPopEnterAnim(R.anim.anim_in_left).setPopExitAnim(R.anim.anim_out_right)
        findNavController().navigate(des, bundle, navBuilder.build())
    }

    fun View.navigateToWithItemClick(des: Int, bundle: Bundle = bundleOf()) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.anim_in_right).setExitAnim(R.anim.anim_out_left)
            .setPopEnterAnim(R.anim.anim_in_left).setPopExitAnim(R.anim.anim_out_right)
        findNavController().navigate(des, bundle, navBuilder.build())
    }

    fun Activity.navigateToActivity(activity: Class<*>, data: Bundle = bundleOf()) {
        val intent = Intent(this, activity)
        intent.putExtras(data)
        startActivity(intent)
    }

    fun Fragment.handleOnBackPressedAppLink() {
        if (requireActivity().intent.data == null)
            findNavController().popBackStack()
        else
            requireActivity().finish()
    }

    fun View.setOnClickView(action: (View) -> Unit) {
        this.setOnClickListener {
            clickAnimation(this.context)
            action(it)
        }
    }

    fun View.setOnClickView(listener: View.OnClickListener) {
        this.setOnClickListener(listener)
    }

    private fun View.clickAnimation(context: Context) {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_click))
    }

    fun TextView.textColor(color: Int) {
        setTextColor(resources.getColor(color))
    }

    fun View.setMargins(
        left: Int = this.marginLeft,
        top: Int = this.marginTop,
        right: Int = this.marginRight,
        bottom: Int = this.marginBottom,
    ) {
        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            setMargins(left, top, right, bottom)
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
