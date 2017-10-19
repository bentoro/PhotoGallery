package com.example.ben.gallery;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by Ben on 2017-10-05.
 */

public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.search)).perform(click());
        //onView(withId(R.id.btn_back)).perform(click());

        // Type text and then press the button.
        //onView(withId(R.id.btn_back)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.startdate)).check(matches(withText("2017-1-8")));
        onView(withId(R.id.btn_end)).perform(click());
        onView(withId(R.id.startdate)).check(matches(withText("2017-6-10")));
        onView(withId(R.id.btn_end)).perform(click());
        //onView(withContentDescription("Navigate up")).perform(click());
    }
}

