package com.example.haki.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.feature_utils.base.BaseActivity
import com.example.haki.R
import com.example.haki.setting.SettingFragment
import com.example.haki.home.homeFragment.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    private val fragmentTags = arrayListOf("Home", "", "Setting")
    private var lastSelectBottomBar: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // custom bottomNavView
        bottomNav.background = null
        bottomNav.menu.getItem(1).isEnabled = false
        switchFragment(fragmentTags[0])
        bottomNav.setOnNavigationItemSelectedListener {
            // set điều kiện thời gian khi click qua lại giữa các button 0,1s
            if (lastSelectBottomBar == -1L || System.currentTimeMillis() - lastSelectBottomBar > 100) {
                when (it.itemId) {
                    R.id.icHome -> {
                        switchFragment(fragmentTags[0])
                        Toast.makeText(this, "home!", Toast.LENGTH_SHORT).show()
                    }
                    R.id.icSetting -> {
                        switchFragment(fragmentTags[2])
                        Toast.makeText(this, "history!", Toast.LENGTH_SHORT).show()
                    }
                }
                lastSelectBottomBar = System.currentTimeMillis()
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    // set select fragment
    private fun switchFragment(switchTag: String) {
        var fragment = getFragmentByTag(switchTag)
        if (fragment != null) {
            showFragment(fragment)
            // duyệt mảng fragmentTags -> show mới, hide cũ đi.
            for (tag in fragmentTags) {
                if (tag != switchTag) {
                    val fragmentToHide = getFragmentByTag(tag)
                    fragmentToHide?.let {
                        hideFragment(it)
                    }
                }
            }
        } else {
            when (switchTag) {
                fragmentTags[0] -> {
                    fragment = HomeFragment.newInstance()
                    addFragment(R.id.container, fragment, switchTag)
                    for (tag in fragmentTags) {
                        if (tag != switchTag) {
                            val fragmentToHide = getFragmentByTag(tag)
                            fragmentToHide?.let {
                                hideFragment(it)
                            }
                        }
                    }
                }
                fragmentTags[2] -> {
                    fragment = SettingFragment.newInstance()
                    addFragment(R.id.container, fragment, switchTag)
                    for (tag in fragmentTags) {
                        if (tag != switchTag) {
                            val fragmentToHide = getFragmentByTag(tag)
                            fragmentToHide?.let {
                                hideFragment(it)
                            }
                        }
                    }
                }
                else -> throw IllegalArgumentException("$switchTag not found in $fragmentTags")
            }
        }
    }

}