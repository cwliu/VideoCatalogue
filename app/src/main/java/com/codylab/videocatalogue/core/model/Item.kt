package com.codylab.videocatalogue.core.model

data class Item(
    val description: String,
    val images: Images,
    val title: String,
    val year: Int
)