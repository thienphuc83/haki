package com.example.feature_utils.base

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.feature_utils.dialog.LoadingDialog

class BaseActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showLoadingDialog(
        canceledOnTouchOutSide: Boolean = true,
        cancelable: Boolean = true,
    ) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.canceledOnTouchOutside(canceledOnTouchOutSide)
        loadingDialog?.cancelable(cancelable)
        loadingDialog?.show()
    }

    fun hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog?.dismiss()
        }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commit()
    }

    fun hideFragment(fragment: Fragment) {
        if (!fragment.isHidden) {
            supportFragmentManager.beginTransaction()
                .hide(fragment)
                .commit()
        }
    }

    fun addFragment(@IdRes containerId: Int, fragment: Fragment, tag: String?) {
        try {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragment, tag)
                .commit()
        } catch (ex: Exception) {
        }
    }

    fun removeFragment(fragment: Fragment) {
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commit()
        }
    }

    fun getFragmentByTag(tag: String?): Fragment? {
        return if (!TextUtils.isEmpty(tag)) {
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            if (fragment != null) {
                fragment
            } else {
                Log.d("BaseActivity", "getFragmentByTag: fragment not found with $tag")
                null
            }
        } else {
            Log.d("BaseActivity", "getFragmentByTag: tag is null")
            null
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("BaseActivity", javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()

        hideLoadingDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}