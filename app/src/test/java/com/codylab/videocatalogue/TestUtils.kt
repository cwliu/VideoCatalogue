package com.codylab.videocatalogue

import com.codylab.videocatalogue.core.model.Category
import com.codylab.videocatalogue.core.model.Images
import com.codylab.videocatalogue.core.model.Item

class TestUtils {
    companion object {
        fun getSampleCategories() = listOf(
            Category(
                "Movies", listOf(
                    Item(
                        "A bright young yeti finds something he thought didn't exist—a human.",
                        Images(
                            "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-portrait.jpg",
                            "\"https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-landscape.jpg\""
                        ),
                        "Smallfoot",
                        2018
                    ),
                    Item(
                        "King T'Challa returns home from America to Wakanda.",
                        Images(
                            "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-portrait.jpg",
                            "\"https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-portrait.jpg\""
                        ),
                        "Black Panther",
                        2018
                    )
                )
            ),
            Category(
                "Features", listOf(
                    Item(
                        "A bright young yeti finds something he thought didn't exist—a human.",
                        Images(
                            "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-portrait.jpg",
                            "\"https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-landscape.jpg\""
                        ),
                        "Smallfoot",
                        2018
                    )
                )
            )
        )
    }
}
