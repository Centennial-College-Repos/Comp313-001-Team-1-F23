package com.gokulraj.neocare.views


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gokulraj.neocare.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomePagePatientActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun homePagePatientActivityTest() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.emailAddressEt),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.emailAddressTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("1@2.com"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.passwordEt),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.passwordTil),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("123456"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btnLogin), withText("Sign In"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_view),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        Thread.sleep(6000) // Sleep for 6 seconds


        val imageView = onView(
            allOf(
                withId(R.id.logo),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.teamMembersLink), withText("Our Team"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.teamMembersLink), withText("Our Team"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Our Team")))

        val textView3 = onView(
            allOf(
                withId(R.id.aboutUsLink), withText("About Us"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.aboutUsLink), withText("About Us"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("About Us")))

        val textView5 = onView(
            allOf(
                withId(R.id.firstAidLink), withText("First Aid Info"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withId(R.id.firstAidLink), withText("First Aid Info"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("First Aid Info")))

        val textView7 = onView(
            allOf(
                withId(R.id.updateProfileLink), withText("Update Profile"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView7.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withId(R.id.updateProfileLink), withText("Update Profile"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("Update Profile")))

        val textView9 = onView(
            allOf(
                withId(R.id.feedbackLink), withText("Feedback"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView9.check(matches(isDisplayed()))

        val textView10 = onView(
            allOf(
                withId(R.id.feedbackLink), withText("Feedback"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView10.check(matches(withText("Feedback")))

        val textView11 = onView(
            allOf(
                withId(R.id.logOutLink), withText("Log Out"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView11.check(matches(isDisplayed()))

        val textView12 = onView(
            allOf(
                withId(R.id.logOutLink), withText("Log Out"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView12.check(matches(withText("Log Out")))

        val textView13 = onView(
            allOf(
                withId(R.id.logOutLink), withText("Log Out"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView13.check(matches(withText("Log Out")))
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
