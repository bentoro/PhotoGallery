package com.example.ben.gallery;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBHandler db = new DBHandler(appContext);
        //db.addimgGallery(new imgGallery(4,"asdf", 2017,11,5));
        List<imgGallery> gallery = db.date(2016,2017,11,12,1,10);

        //assertEquals(4,gallery.size());
        for (int i = 0; i<=gallery.size()-1; i++){
            //System.out.println("image: "+gallery.get(gallery.size()-1).getImage()+"Date: "+gallery.get(gallery.size()-1).getYear()+"-"+gallery.get(gallery.size()-1).getMonth()+"-"+gallery.get(gallery.size()-1).getDay()) ;
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 12);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);


        }
        //System.out.println("image: "+gallery.get(gallery.size()-1)+"Date: "+gallery.get(gallery.size()-1).getYear()+"-"+gallery.get(gallery.size()-1).getMonth()+"-"+gallery.get(gallery.size()-1).getDay()) ;

        //assertEquals("a", appContext.getPackageName());
    }
}
