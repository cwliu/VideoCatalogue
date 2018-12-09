package com.codylab.videocatalogue.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.codylab.videocatalogue.R
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        onView(allOf(withId(R.id.categoryName), withText("Features"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.categoryName), withText("Movies"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.categoryName), withText("TV Shows"))).check(matches(isDisplayed()))

        onView(firstView(withId(R.id.videoCell))).perform(click())

        onView(withId(R.id.closeButton)).check(matches(isDisplayed()))
        onView(withId(R.id.year)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))

        onView(firstView(withId(R.id.closeButton))).perform(click())

        onView(allOf(withId(R.id.categoryName), withText("Features"))).check(matches(isDisplayed()))
    }

    // https://stackoverflow.com/a/45932587/475242
    private fun <T> firstView(matcher: Matcher<T>): Matcher<T> {
        return object : BaseMatcher<T>() {
            var isFirst = true

            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }

                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }

}
