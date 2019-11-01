package com.aniketkadam.distancesorter.distancecalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.aniketkadam.distancesorter.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun has_expected_data() {
        onView(withId(R.id.customerList)).perform(
            RecyclerViewActions.actionOnItem<CustomerViewHolder>(
                hasDescendant(withText("Olive Ahearn")),
                scrollTo()
            )
        )
    }
}