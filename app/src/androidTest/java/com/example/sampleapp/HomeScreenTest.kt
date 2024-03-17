package com.example.sampleapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sampleapp.data.adapter.DataAdapter
import com.example.sampleapp.view.MainActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun homeFragment_visibleOnAppLaunch() {
        Thread.sleep(2000) //delay while load data
        onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //progress bar not visible anymore
        onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun recyclerView_itemClick_OpensDetailFragment(){
        Thread.sleep(1500) //delay while load data
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<DataAdapter.ItemViewHolder>(5))
            .perform(RecyclerViewActions.actionOnItemAtPosition<DataAdapter.ItemViewHolder>(5, click()))

        onView(withId(R.id.detailView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}