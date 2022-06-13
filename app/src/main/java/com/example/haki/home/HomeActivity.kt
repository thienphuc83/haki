package com.example.haki.home

import android.os.Bundle
import android.util.Log
import com.example.feature_utils.base.BaseActivity
import com.example.haki.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("nai","hh")
    }
}