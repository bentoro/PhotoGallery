package com.example.ben.gallery;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
        DBHandler db = new DBHandler(appContext);
        //Id: 4 ,Location: White Avenue ,Caption: Image path: /storage/emulated/0/DCIM/Camera/20171019_123559.jpg ,Time: 2017/9/5
        //db.addimgGallery(new imgGallery(1,"White Avenue","Unit testing","/storage/emulated/0/DCIM/Camera/20171019_123559.jpg",2017,11,5));
        List<imgGallery> gallery = db.date(db.getAllimgGallerys(),2017,2017,11,11,1,10);

        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 11);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);
        }
        gallery = db.location(db.getAllimgGallerys(),"White Avenue");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getLocation() == "White Avenue");
        }

        gallery = db.caption(db.getAllimgGallerys(),"Unit testing");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getCaption() == "Unit testing");
        }

        gallery = db.dateLocation(db.getAllimgGallerys(),2017,2017,11,11,1,10,"White Avenue");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getLocation() == "White Avenue");
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 11);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);
        }

        gallery = db.dateCaption(db.getAllimgGallerys(),2017,2017,11,11,1,10,"Unit testing");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getCaption() == "Unit testing");
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 11);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);
        }

        gallery = db.dateCaptionLocation(db.getAllimgGallerys(),2017,2017,11,11,1,10,"Unit testing","White Avenue");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getCaption() == "Unit testing");
            assertTrue( gallery.get(i).getLocation() == "White Avenue");
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 11);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);
        }

        gallery = db.CaptionLocation(db.getAllimgGallerys(),"Unit testing","White Avenue");
        for (int i = 0; i<=gallery.size()-1; i++){
            assertTrue( gallery.get(i).getLocation() == "White Avenue");
            assertTrue( gallery.get(i).getCaption() == "Unit testing");

        }

    }


}
