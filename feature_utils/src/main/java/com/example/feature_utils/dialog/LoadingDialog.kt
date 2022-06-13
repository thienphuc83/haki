package com.example.feature_utils.dialog

import android.content.Context
import com.example.feature_utils.R

class LoadingDialog(context: Context) :BaseDialog(context) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_loading
    }
}