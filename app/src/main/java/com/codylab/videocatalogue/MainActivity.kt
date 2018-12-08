package com.codylab.videocatalogue

import android.os.Bundle
import com.codylab.videocatalogue.core.CatalogueRepository
import dagger.android.DaggerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : DaggerActivity(), CoroutineScope {

    var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @Inject
    lateinit var catalogueRepository: CatalogueRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val categories = catalogueRepository.getCategories()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}
