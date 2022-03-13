package com.srn.satellitelist.ui

import com.srn.satellitelist.R
import com.srn.satellitelist.databinding.ActivityMainBinding
import com.srn.satellitelist.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by SoheilR .
 */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initLayout(): Int = R.layout.activity_main
    override fun onCreate() {}

}