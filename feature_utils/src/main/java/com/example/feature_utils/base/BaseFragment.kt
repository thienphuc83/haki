package com.example.feature_utils.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.d("BaseFragment", javaClass.simpleName)
    }

    fun showLoadingDialog(
        canceledOnTouchOutSide: Boolean = true,
        cancelable: Boolean = true
    ) {
        if (requireActivity() is BaseActivity) {
            (requireActivity() as BaseActivity).showLoadingDialog(
                canceledOnTouchOutSide,
                cancelable
            )
        }
    }

    fun hideLoadingDialog() {
        if (requireActivity() is BaseActivity) {
            (requireActivity() as BaseActivity).hideLoadingDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}