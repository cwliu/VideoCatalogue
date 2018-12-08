package com.codylab.videocatalogue.core.api

import com.codylab.videocatalogue.utils.BaseApiTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueApiTest : BaseApiTest() {

    @Test
    fun getVideos() = runBlocking {
        // Given
        val catalogueAPI = getRetrofitApi(CatalogueAPI::class.java)
        val mockResponse = MockResponse().setBody(GET_CATEGORIES_MOCK_RESPONSE)
            .setHeader("content-type", "application/json")
        server.enqueue(mockResponse)

        // When
        val result = catalogueAPI.getCategories().await()

        // Then
        Assert.assertEquals(3, result.size)
        Assert.assertEquals("Movies", result[0].category)
        Assert.assertEquals("Smallfoot", result[0].items[0].title)
        Assert.assertEquals(2018, result[0].items[0].year)
        Assert.assertEquals(
            "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-portrait.jpg",
            result[0].items[0].images.portrait
        )
        Assert.assertEquals(
            "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-landscape.jpg",
            result[0].items[0].images.landscape
        )
    }
}

const val GET_CATEGORIES_MOCK_RESPONSE = """[
        {
            "category": "Movies",
            "items": [
            {
                "title": "Smallfoot",
                "year": 2018,
                "description": "A bright young yeti finds something he thought didn't exist—a human.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-landscape.jpg"
            }
            },
            {
                "title": "Black Panther",
                "year": 2018,
                "description": "King T'Challa returns home from America to Wakanda.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-landscape.jpg"
            }
            },
            {
                "title": "Avengers: Infinity War",
                "year": 2018,
                "description": "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/avengers-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/avengers-landscape.jpg"
            }
            },
            {
                "title": "Johnny English Strikes Again",
                "year": 2018,
                "description": "Disaster strikes when a criminal mastermind reveals the identities of all active undercover agents in Britain.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/english-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/english-landscape.jpg"
            }
            },
            {
                "title": "The Predator",
                "year": 2018,
                "description": "From the outer reaches of space to the small-town streets of suburbia, the hunt comes home.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/preditor-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/preditor-landscape.jpg"
            }
            },
            {
                "title": "Venom",
                "year": 2018,
                "description": "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego \"Venom\" to save his life.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/venom-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/venom-landscape.jpg"
            }
            },
            {
                "title": "Dunkirk",
                "year": 2017,
                "description": "The story of the miraculous evacuation of Allied soldiers.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/dunkurk-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/dunkurk-landscape.jpg"
            }
            },
            {
                "title": "The Equalizer",
                "year": 2014,
                "description": "In The Equalizer, Denzel Washington plays McCall, a man who believes he has put his mysterious past behind him and dedicated himself to beginning a new, quiet life.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/equalizer-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/equalizer-landscape.jpg"
            }
            },
            {
                "title": "Whiplash",
                "year": 2014,
                "description": "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/whiplash-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/whiplash-landscape.jpg"
            }
            },
            {
                "title": "Upgrade",
                "year": 2018,
                "description": "A brutal mugging leaves Grey Trace paralyzed in the hospital and his beloved wife dead. ",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/upgrade-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/upgrade-landscape.jpg"
            }
            },
            {
                "title": "Pulp Fiction",
                "year": 1994,
                "description": "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/pulp-fiction-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/pulp-fiction-landscape.jpg"
            }
            },
            {
                "title": "The Nun",
                "year": 2018,
                "description": "When a young nun at a cloistered abbey in Romania takes her own life.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-nun-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-nun-landscape.jpg"
            }
            }
            ]
        },
        {
            "category": "TV Shows",
            "items": [
            {
                "title": "Game of Thrones",
                "year": 2017,
                "description": "Nine noble families fight for control over the mythical lands of Westeros.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/game-of-thrones-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/game-of-thrones-landscape.jpg"
            }
            },
            {
                "title": "Twin Peaks",
                "year": 2017,
                "description": "Picks up 25 years after the inhabitants of a quaint northwestern town are stunned when their homecoming queen is murdered.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/twin-peaks-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/twin-peaks-landscape.jpg"
            }
            },
            {
                "title": "Supergirl",
                "year": 2016,
                "description": "Superman’s cousin, Kara Danvers (aka Supergirl) balances her work as a reporter.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/supergirl-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/supergirl-landscape.jpg"
            }
            },
            {
                "title": "American Gods",
                "year": 2017,
                "description": "A  recently released ex-convict named Shadow meets a mysterious man who calls himself \"Wednesday\".",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-gods-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-gods-landscape.jpg"
            }
            },
            {
                "title": "Westworld",
                "year": 2016,
                "description": "Westworld is an American science fiction western thriller television series created by Jonathan Nolan and Lisa Joy for HBO.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/westworld-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/westworld-landscape.jpg"
            }
            },
            {
                "title": "Stranger Things",
                "year": 2016,
                "description": "Stranger Things is an American science fiction-horror web television series.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/stranger-things-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/stranger-things-landscape.jpg"
            }
            },
            {
                "title": "Veep",
                "year": 2016,
                "description": "Veep is an American political satire comedy television series, starring Julia Louis-Dreyfus, that premiered on HBO on April 22, 2012.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/veep-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/veep-landscape.jpg"
            }
            },
            {
                "title": "Better Call Saul",
                "year": 2016,
                "description": "Better Call Saul is an American television crime drama series created by Vince Gilligan and Peter Gould.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/better-call-saul-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/better-call-saul-landscape.jpg"
            }
            },
            {
                "title": "The Flash",
                "year": 2017,
                "description": "After being struck by lightning, Barry Allen wakes up from his coma to discover he's been given the power of super speed.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-flash-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-flash-landscape.jpg"
            }
            },
            {
                "title": "The Americans",
                "year": 2016,
                "description": "The Americans is an American television period drama series created and produced by former CIA officer Joe Weisberg.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-americans-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/the-americans-landscape.jpg"
            }
            },
            {
                "title": "American Crime Story",
                "year": 2016,
                "description": "American Crime Story is an American true crime anthology television series.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-crime-story-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-crime-story-landscape.jpg"
            }
            }
            ]
        },
        {
            "category": "Features",
            "items": [
            {
                "title": "Smallfoot",
                "year": 2018,
                "description": "A bright young yeti finds something he thought didn't exist—a human.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/smallfoot-landscape.jpg"
            }
            },
            {
                "title": "Black Panther",
                "year": 2018,
                "description": "King T'Challa returns home from America to Wakanda.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/black-panther-landscape.jpg"
            }
            },
            {
                "title": "Avengers: Infinity War",
                "year": 2018,
                "description": "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/avengers-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/avengers-landscape.jpg"
            }
            },
            {
                "title": "Johnny English Strikes Again",
                "year": 2018,
                "description": "Disaster strikes when a criminal mastermind reveals the identities of all active undercover agents in Britain.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/english-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/english-landscape.jpg"
            }
            },
            {
                "title": "The Predator",
                "year": 2018,
                "description": "From the outer reaches of space to the small-town streets of suburbia, the hunt comes home.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/preditor-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/preditor-landscape.jpg"
            }
            },
            {
                "title": "Twin Peaks",
                "year": 2017,
                "description": "Picks up 25 years after the inhabitants of a quaint northwestern town are stunned when their homecoming queen is murdered.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/twin-peaks-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/twin-peaks-landscape.jpg"
            }
            },
            {
                "title": "Supergirl",
                "year": 2016,
                "description": "Superman’s cousin, Kara Danvers (aka Supergirl) balances her work as a reporter.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/supergirl-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/supergirl-landscape.jpg"
            }
            },
            {
                "title": "American Gods",
                "year": 2017,
                "description": "A  recently released ex-convict named Shadow meets a mysterious man who calls himself \"Wednesday\".",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-gods-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/american-gods-landscape.jpg"
            }
            },
            {
                "title": "Westworld",
                "year": 2016,
                "description": "Westworld is an American science fiction western thriller television series created by Jonathan Nolan and Lisa Joy for HBO.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/westworld-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/westworld-landscape.jpg"
            }
            },
            {
                "title": "Stranger Things",
                "year": 2016,
                "description": "Stranger Things is an American science fiction-horror web television series.",
                "images": {
                "portrait": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/stranger-things-portrait.jpg",
                "landscape": "https://s3-ap-southeast-2.amazonaws.com/video-catalogue/images/stranger-things-landscape.jpg"
            }
            }
            ]
        }
    ]"""