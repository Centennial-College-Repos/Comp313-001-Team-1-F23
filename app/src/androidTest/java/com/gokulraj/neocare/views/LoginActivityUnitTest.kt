package com.gokulraj.neocare.views


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gokulraj.neocare.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityUnitTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginActivityUnitTest() {
        val textView = onView(
            allOf(
                withId(R.id.registerRedirect), withText("Don't have an account? Register now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Don't have an account? Register now!")))

        val textView2 = onView(
            allOf(
                withId(R.id.registerRedirect), withText("Don't have an account? Register now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Don't have an account? Register now!")))

        val textView3 = onView(
            allOf(
                withId(R.id.registerRedirect), withText("Don't have an account? Register now!"),
                withParent(
                    allOf(
                        withId(R.id.txtLoginCredential),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withId(R.id.requestImageView),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withText("Emergency Services"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Emergency Services")))


        val textView5 = onView(
            allOf(
                withText("Voice Command for Emergency Ambulance Services"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Voice Command for Emergency Ambulance Services")))

        val imageView2 = onView(
            allOf(
                withId(R.id.emtLogin),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withText("Emergency Technician"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Emergency Technician")))

        val textView7 = onView(
            allOf(
                withText("Welcome"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("Welcome")))

        val textView8 = onView(
            allOf(
                withText("Please sign in with your credentials."),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("Please sign in with your credentials.")))



        val button = onView(
            allOf(
                withId(R.id.btnLogin), withText("Sign In"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView9 = onView(
            allOf(
                withId(R.id.forgetRedirect), withText("Forget password?"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView9.check(matches(withText("Forget password?")))

        val textView10 = onView(
            allOf(
                withId(R.id.forgetRedirect), withText("Forget password?"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView10.check(matches(isDisplayed()))

        val textView11 = onView(
            allOf(
                withId(R.id.forgetRedirect), withText("Forget password?"),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        textView11.check(matches(isDisplayed()))
    }
}
