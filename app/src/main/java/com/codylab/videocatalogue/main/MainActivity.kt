package com.codylab.videocatalogue.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.codylab.videocatalogue.R
import com.codylab.videocatalogue.catalogue.CatalogueFragment
import com.codylab.videocatalogue.core.model.Item
import com.codylab.videocatalogue.detail.DetailFragment
import com.codylab.videocatalogue.detail.DetailFragmentNavigator


class MainActivity : AppCompatActivity(), DetailFragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment, CatalogueFragment())
                .commit()
        }
    }

    override fun openDetailFragment(item: Item) {
        val fragment = DetailFragment.newInstance(item)
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
            .add(R.id.fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}
