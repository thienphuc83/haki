package com.example.haki.home

import android.os.Bundle
import com.example.feature_utils.base.BaseActivity
import com.example.haki.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // custom bottomNavView
        bottomNav.background = null
        bottomNav.menu.getItem(2).isEnabled = false
    }
}