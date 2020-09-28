package id.ac.ui.cs.mobileprogramming.irfanazizalamin.helloworld

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_activityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_increaseNumberClicked() {
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.information)).check(matches(withText("increase the number")))
    }

    @Test
    fun test_decreaseNumberClicked() {
        onView(withId(R.id.buttonMinus)).perform(click())
        onView(withId(R.id.information)).check(matches(withText("decrease the number")))
    }
}