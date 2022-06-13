package com.example.feature_utils.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.annotation.LayoutRes
import com.example.feature_utils.R

abstract class BaseDialog(context: Context) : Dialog(context) {
    @LayoutRes
    abstract fun getLayoutId(): Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (shouldTransparent()) {
            if (window != null) {
                window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window!!.setWindowAnimations(R.style.DialogTheme)
            }
        }

        setContentView(getLayoutId())
    }

    open fun shouldTransparent(): Boolean {
        return true
    }

    open fun cancelable(cancelable: Boolean): BaseDialog {
        setCancelable(cancelable)
        return this
    }

    open fun canceledOnTouchOutside(canceled: Boolean): BaseDialog {
        setCanceledOnTouchOutside(canceled)
        return this
    }

    override fun dismiss() {
        super.dismiss()
        setOnCancelListener(null)
    }
}