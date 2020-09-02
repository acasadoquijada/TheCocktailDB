package com.example.thecocktaildb

import android.app.Instrumentation
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DrinkFragmentInstrumentedTest{

    @get:Rule
    val mainActivityActivityTestRule =
        IntentsTestRule(
            MainActivity::class.java
        )


    fun infoIsShown() {
        checkTitleIsNotEmpty()
        checkTypeAndGlassIsNotEmpty()
        checkInstructionsIsNotEmpty()
        checkIngredientRecyclerView()
    }

    @Test
    fun checkShareButton() {

        val expectedIntent = createExpectedIntent()

        intending(anyIntent())
            .respondWith(Instrumentation.ActivityResult(0, null))

        onView(withId(R.id.action_share)).perform(click())
        intended(expectedIntent)

    }

    private fun createExpectedIntent(): Matcher<Intent>? {
        return allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtraWithKey(Intent.EXTRA_INTENT)
        )
    }


    private fun checkTitleIsNotEmpty(){
        textIsNotEmpty(R.id.cocktailTitle)
    }

    private fun checkTypeAndGlassIsNotEmpty(){
        textIsNotEmpty(R.id.typeAndGlass)
    }

    private fun checkInstructionsIsNotEmpty(){
        textIsNotEmpty(R.id.instructions)
    }

    private fun textIsNotEmpty(id: Int){
        onView(withId(id)).check(matches(not(withText(""))))
    }

    private fun checkIngredientRecyclerView(){
        checkIngredientNameIsNotEmpty()
        checkIngredientMeasureIsNotEmpty()
    }

    private fun checkIngredientNameIsNotEmpty(){
        checkRecyclerViewElementIsNotEmpty(R.id.name)
    }

    private fun checkIngredientMeasureIsNotEmpty(){
        checkRecyclerViewElementIsNotEmpty(R.id.measure)
    }

    private fun checkRecyclerViewElementIsNotEmpty(id: Int){
        onView(withId(R.id.ingredientRecyclerView)).check(
            matches(recyclerViewAtPositionOnView(0, not(withText("")), id)))
    }

    // Obtained from: https://medium.com/mindorks/some-useful-custom-espresso-matchers-in-android-33f6b9ca2240

    private fun recyclerViewAtPositionOnView(
        position: Int,
        itemMatcher: Matcher<View?>,
        targetViewId: Int
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                val targetView =
                    viewHolder?.itemView?.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }

}