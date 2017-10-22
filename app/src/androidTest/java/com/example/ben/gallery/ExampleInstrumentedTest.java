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
        List<imgGallery> gallery = db.date(2017,2017,11,11,1,10);
        //List<imgGallery> gallery = db.date(2016,2017,11,12,1,10);



        //assertEquals(4,gallery.size());
        for (int i = 0; i<=gallery.size()-1; i++){
            System.out.println("image: "+gallery.get(i).getImage()+"Date: "+gallery.get(i).getYear()+"-"+gallery.get(i).getMonth()+"-"+gallery.get(i).getDay()) ;
            assertTrue( gallery.get(i).getYear() >= 2016);
            assertTrue( gallery.get(i).getYear() <= 2017);
            assertTrue( gallery.get(i).getMonth() >= 11);
            assertTrue( gallery.get(i).getMonth() <= 11);
            assertTrue( gallery.get(i).getDay() >= 1);
            assertTrue( gallery.get(i).getDay() <= 10);
            //assertEquals(5,gallery.get(i).getDay());
            //assertEquals("11",gallery.get(i).getMonth());
            //assertEquals("2017",gallery.get(i).getYear());


        }
        //System.out.println("image: "+gallery.get(gallery.size()-1)+"Date: "+gallery.get(gallery.size()-1).getYear()+"-"+gallery.get(gallery.size()-1).getMonth()+"-"+gallery.get(gallery.size()-1).getDay()) ;

        //assertEquals("a", appContext.getPackageName());


    }


}
