package com.example.foryoudicodingkadesubtwo.search

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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

        onView(withId(action_search)).perform(click());

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("ar"),
            pressImeActionButton()
        )
        Thread.sleep(2000)
        onView(isAssignableFrom(EditText::class.java)).perform(clearText())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("ma"),
            pressImeActionButton()
        )

        Thread.sleep(2000)


    }
}


