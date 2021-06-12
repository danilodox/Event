package com.br.events

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.br.events.ui.eventCheckin.CheckinActivity
import com.br.events.ui.util.Validation
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CheckinActivityTest {
    @get:Rule
    val mActivityRule = ActivityScenarioRule(CheckinActivity::class.java)


    /**
     * check if the components are visible to the user
     */
    @Test
    fun ensureThatComponentsAreVisible() {

        Espresso.onView(ViewMatchers.withId(R.id.input_checkin_nome)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.input_checkin_email)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.editText_checkin_nome)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.editText_checkin_email)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.button_checkin)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.progress_loading_checkin)).check(
            ViewAssertions.matches(
                Matchers.not(ViewMatchers.isDisplayed())
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.error_network_checkin)).check(
            ViewAssertions.matches(
                Matchers.not(ViewMatchers.isDisplayed())
            )
        )


    }

    @Test
    fun validationEmail() {
        //Email without the at sign
        val email1 = Validation.isEmailValid(
            "danilomedeiros.doxgmail.com"
        )
        assertFalse(email1)

        //Email without the .com
        val email2 = Validation.isEmailValid(
            "danilomedeiros.dox@gmail"
        )
        assertFalse(email2)

        //Email without the address
        val email3 = Validation.isEmailValid(
            "danilomedeirosdox.com"
        )
        assertFalse(email3)

        //typing the correct email
        val email4 = Validation.isEmailValid(
            "danilomedeiros.dox@gmail.com"
        )
        assertTrue(email4)
    }


    @Test
    fun validationName() {
        //check the name is not blank or empty
        val name1 = Validation.validateText("")
        assertFalse(name1)

        val name2 = Validation.validateText(" ")
        assertFalse(name2)

        //according to domain rules, a person's name must be at least two letters.
        val name3 = Validation.validateText("a")
        assertFalse(name3)
    }
}