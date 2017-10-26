package com.example.ben.gallery;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Ben on 2017-10-05.
 */

public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void stubCamera(){
        ActivityResult result = createImage();

        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
    }


    @Test
    public void takePhoto() {
        // Type text and then press the button.
        //onView(withId(R.id.search)).perform(click());
        //onView(withId(R.id.btn_back)).perform(click());
        // Type text and then press the button.
        //onView(withId(R.id.btn_back)).perform(click());
        // Check that the text was changed.
        //onView(withId(R.id.startdate)).check(matches(withText("2017-1-8")));
        onView(withId(R.id.btn_capture)).perform(click());
        //onView(withId(R.id.imageView)).check(matches(hasDrawable()));
        //onView(withId(R.id.btn_end)).perform(click());
        //onView(withId(R.id.startdate)).check(matches(withText("2017-6-10")));
        //onView(withId(R.id.btn_end)).perform(click());
        //onView(withContentDescription("Navigate up")).perform(click());
    }

    private ActivityResult createImage(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(mActivityRule.getActivity().getResources(),R.drawable.totoro));

        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new ActivityResult(Activity.RESULT_OK, resultData);
    }



}

