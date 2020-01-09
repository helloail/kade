package com.example.foryoudicodingkadesubtwo.search

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.id.action_search
import com.example.foryoudicodingkadesubtwo.R.id.search_recycler
import com.example.foryoudicodingkadesubtwo.view.activity.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {

//       click menu search
        onView(withId(action_search)).perform(click())


        //input and search "ar"
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("arsenal"),
            pressImeActionButton()
        )

        Thread.sleep(2000)

        //show on recycler
        onView(withId(search_recycler)).check(matches(isDisplayed()))

        onView(withId(search_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        //click favorite
        Thread.sleep(2000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(2000)


        Espresso.pressBack()

        //clear and search "la"
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("la"),
            pressImeActionButton()
        )

        Thread.sleep(2000)

        //show detail on recycler
        onView(withId(search_recycler)).check(matches(isDisplayed()))
        onView(withId(search_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        Thread.sleep(2000)

//        click favorite
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())

        Thread.sleep(2000)

        Espresso.pressBack()

//        clear data and finnish
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())


    }
}


