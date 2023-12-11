package com.gokulraj.neocare.views


import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gokulraj.neocare.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread

@LargeTest
@RunWith(AndroidJUnit4::class)
class RegistrationActivityUnitTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    // Custom Idling Resource for animations
    object AnimationIdlingResource {
        private const val RESOURCE = "AnimationIdlingResource"
        private val countingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            countingIdlingResource.increment()
        }

        fun decrement() {
            countingIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource {
            return countingIdlingResource
        }
    }

    @Before
    fun setUp() {
        // Register the custom IdlingResource
        IdlingRegistry.getInstance().register(AnimationIdlingResource.getIdlingResource())
    }

    @After
    fun tearDown() {
        // Unregister the custom IdlingResource
        IdlingRegistry.getInstance().unregister(AnimationIdlingResource.getIdlingResource())
    }

    @Test
    fun registrationActivityUnitTest() {
        Thread.sleep(2000) // Adjust the wait time as needed
        //onView(withId(R.id.registerRedirect)).check(matches(isDisplayed())).perform(click())



        Thread.sleep(6000) // Sleep for 6 seconds



        val textView = onView(
            allOf(
                withText("Welcome"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Welcome")))

        val textView2 = onView(
            allOf(
                withText("Please register with your information."),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Please register with your information.")))

        val textView3 = onView(
            allOf(
                withText("Select user type"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Select user type")))

        val radioButton = onView(
            allOf(
                withId(R.id.radioButtonPatient), withText("Patient"),
                withParent(
                    allOf(
                        withId(R.id.radioGroup),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton.check(matches(isDisplayed()))

        val radioButton2 = onView(
            allOf(
                withId(R.id.radioButtonHealthcareProfessional), withText("Healthcare Professional"),
                withParent(
                    allOf(
                        withId(R.id.radioGroup),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton2.check(matches(isDisplayed()))

        val editText = onView(
            allOf(
                withId(R.id.fullNameEt), withText("Full Name"),
                withParent(withParent(withId(R.id.fullNameTil))),
                isDisplayed()
            )
        )
        editText.check(matches(isDisplayed()))

        val editText2 = onView(
            allOf(
                withId(R.id.fullNameEt), withText("Full Name"),
                withParent(withParent(withId(R.id.fullNameTil))),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("Full Name")))

        val editText3 = onView(
            allOf(
                withId(R.id.healthCardNumberEt), withText("Health Card Number"),
                withParent(withParent(withId(R.id.healthCardNumberTil))),
                isDisplayed()
            )
        )
        editText3.check(matches(isDisplayed()))

        val editText4 = onView(
            allOf(
                withId(R.id.healthCardNumberEt), withText("Health Card Number"),
                withParent(withParent(withId(R.id.healthCardNumberTil))),
                isDisplayed()
            )
        )
        editText4.check(matches(withText("Health Card Number")))

        val editText5 = onView(
            allOf(
                withId(R.id.emailAddressEt), withText("Email Address"),
                withParent(withParent(withId(R.id.emailAddressTil))),
                isDisplayed()
            )
        )
        editText5.check(matches(isDisplayed()))

        val editText6 = onView(
            allOf(
                withId(R.id.emailAddressEt), withText("Email Address"),
                withParent(withParent(withId(R.id.emailAddressTil))),
                isDisplayed()
            )
        )
        editText6.check(matches(withText("Email Address")))

        val editText7 = onView(
            allOf(
                withId(R.id.passwordEt), withText("Password"),
                withParent(withParent(withId(R.id.passwordTil))),
                isDisplayed()
            )
        )
        editText7.check(matches(isDisplayed()))

        val editText8 = onView(
            allOf(
                withId(R.id.passwordEt), withText("Password"),
                withParent(withParent(withId(R.id.passwordTil))),
                isDisplayed()
            )
        )
        editText8.check(matches(withText("Password")))

        val checkBox = onView(
            allOf(
                withId(R.id.termsCb), withText("I agree to the terms and conditions."),
                withParent(
                    allOf(
                        withId(R.id.termsTil),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        checkBox.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.btnRegister), withText("Sign Up"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val linearLayout = onView(
            allOf(
                withId(R.id.txtLoginCredential),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.loginRedirect), withText("Have an account? Log In now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Have an account? Log In now!")))

        val textView5 = onView(
            allOf(
                withId(R.id.loginRedirect), withText("Have an account? Log In now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withId(R.id.loginRedirect), withText("Have an account? Log In now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
