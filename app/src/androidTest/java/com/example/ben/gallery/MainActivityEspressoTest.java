package com.example.ben.gallery;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.io.ByteStreams;
import android.support.test.espresso.core.deps.guava.io.Resources;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.intent.IntentCallback;
import android.support.test.runner.intent.IntentMonitorRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static com.example.ben.gallery.ImageViewMatcher.hasDrawable;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import android.support.test.InstrumentationRegistry.*;


/**
 * Created by Ben on 2017-10-05.
 */

public class MainActivityEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    //public IntentsTestRule<Capture> mIntentsRule = new IntentsTestRule<>(Capture.class);

/*
    @Test
    public void cameraTest() {
        //ActivityResult result = createImageCaptureActivityResultStub();
        //intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
        onView(withId(R.id.btn_capture)).perform(click());
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(
                new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
        IntentCallback intentCallback = new IntentCallback() {
            @Override
            public void onIntentSent(Intent intent) {
                if (intent.getAction().equals("android.media.action.IMAGE_CAPTURE")) {
                    try {
                        Uri imageUri = intent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
                        Context context = InstrumentationRegistry.getTargetContext();
                        Bitmap icon = BitmapFactory.decodeResource(
                                context.getResources(),
                                R.drawable.asdf);
                        OutputStream out = mIntentsRule.getActivity().getContentResolver().openOutputStream(imageUri);
                        icon.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        //GenericUtility.handleException(e);
                    }
                }
            }
        };
        IntentMonitorRegistry.getInstance().addIntentCallback(intentCallback);
        //onView(withId(R.id.imageView)).check(matches(hasDrawable()));
    }

    private  ActivityResult createImageCaptureActivityResultStub() {
        // Put the drawable in a bundle.
        //Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + InstrumentationRegistry.getTargetContext().getResources().getResourcePackageName(R.mipmap.ic_launcher));
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(
                mIntentsRule.getActivity().getResources(), R.drawable.totoro));
        Intent resultData = new Intent();
        resultData.putExtras(bundle);
        // Create the Intent that will include the bundle.

        // Create the ActivityResult with the Intent.
        return new ActivityResult(Activity.RESULT_OK, resultData);
    }*/

    @Test
    public void searchDate() {
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.strt_txt)).check(matches(withText("2017-9-4")));
        onView(withId(R.id.end_txt)).check(matches(withText("2017-9-5")));
        onView(withId(R.id.btn_end)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
    }
    @Test
    public void searchLocation() {
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
        onView(withId(R.id.btn_location)).perform(click());
        onView(withId(R.id.txt_location)).check(matches(withText("rosebank")));
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
    }

    @Test
    public void searchCaption() {
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
        onView(withId(R.id.btn_s)).perform(click());
        onView(withId(R.id.caption_srch)).check(matches(withText("test")));
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));

    }

    @Test
    public void searchALL() {
        onView(withId(R.id.btn_exit)).perform(click());
        onView(withId(R.id.btn_location)).perform(click());
        onView(withId(R.id.txt_location)).check(matches(withText("rosebank")));
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
        onView(withId(R.id.btn_s)).perform(click());
        onView(withId(R.id.caption_srch)).check(matches(withText("test")));
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.strt_txt)).check(matches(withText("2017-9-4")));
        onView(withId(R.id.end_txt)).check(matches(withText("2017-9-5")));
        onView(withId(R.id.btn_end)).perform(click());
        onView(withId(R.id.gallery)).check(matches(hasDrawable()));

    }
}

