package com.codylab.videocatalogue.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.core.model.Item
import com.codylab.videocatalogue.detail.DetailFragment


class MainActivity : AppCompatActivity(), DetailFragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openDetailFragment(item: Item) {
        val fragment = DetailFragment.newInstance(item)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
    }
}
