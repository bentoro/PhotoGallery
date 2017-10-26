package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import static com.example.ben.gallery.R.id.gallery;

public class MainActivity extends AppCompatActivity {
    int min;
    int max;
    int current = 0;
    Button nxt;
    Button prev;
    ImageView image;
    EditText capt;
    boolean date = false;
    boolean caption = false;
    boolean locat = false;
    int y1;
    int y2;
    int m1;
    int m2;
    int d1;
    int d2;
    String cap;
    String location;
    Uri uri;
    int sz;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.gallery);
        boolean test = img.getDrawable() != null;
        System.out.println("RESULT: !!!! : "+test);
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.contains("y1") && settings.contains("y2")&& settings.contains("m1")&& settings.contains("m2")
                && settings.contains("d1")&& settings.contains("d2")) {
            DBHandler db = new DBHandler(this);
            y1=settings.getInt("y1",min);
            m1=settings.getInt("m1",min);
            d1=settings.getInt("d1",min);
            y2=settings.getInt("y2",min);
            m2=settings.getInt("m2",min);
            d2=settings.getInt("d2",min);
            List<imgGallery> gallery = db.date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
            max = gallery.size()-1;
            sz=gallery.size();
            date = true;
            System.out.println("date set");

        } if(settings.contains("caption")){
            DBHandler db = new DBHandler(this);
            cap=settings.getString("caption"," ");
            List<imgGallery> gallery = db.caption(db.getAllimgGallerys(),cap);
            max = gallery.size()-1;
            sz=gallery.size();
            caption = true;
            System.out.println("location: "+location);
            System.out.println("date: "+date);
            System.out.println("caption set");
        } if (settings.contains("location")){
            location = settings.getString("location", " ");
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.location(db.getAllimgGallerys(),location);
            max = gallery.size()-1;
            sz=gallery.size();
            locat = true;
            System.out.println("location set");
        } if (settings.contains("y1") && settings.contains("y2")&& settings.contains("m1")&& settings.contains("m2")
                && settings.contains("d1")&& settings.contains("d2") && settings.contains("caption")){
            y1=settings.getInt("y1",min);
            m1=settings.getInt("m1",min);
            d1=settings.getInt("d1",min);
            y2=settings.getInt("y2",min);
            m2=settings.getInt("m2",min);
            d2=settings.getInt("d2",min);
            cap = settings.getString("caption","");
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.dateCaption(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap);
            max = gallery.size()-1;
            sz = gallery.size();
            caption = true;
            date = true;
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
            System.out.println("caption date set");
        } if (settings.contains("y1") && settings.contains("y2")&& settings.contains("m1")&& settings.contains("m2")
                && settings.contains("d1")&& settings.contains("d2") && settings.contains("caption") && settings.contains("location")) {
            y1=settings.getInt("y1",min);
            m1=settings.getInt("m1",min);
            d1=settings.getInt("d1",min);
            y2=settings.getInt("y2",min);
            m2=settings.getInt("m2",min);
            d2=settings.getInt("d2",min);
            cap = settings.getString("caption","");
            location=settings.getString("location","");
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.dateCaptionLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap,location);
            max = gallery.size()-1;
            sz = gallery.size();
            date = true;
            caption = true;
            locat = true;
            System.out.println("caption: "+caption);
            System.out.println("location: "+location);
            System.out.println("date: "+date);
            System.out.println("ALL set");
        } if(settings.contains("y1") && settings.contains("y2")&& settings.contains("m1")&& settings.contains("m2")
                && settings.contains("d1")&& settings.contains("d2") && settings.contains("location")){
            location=settings.getString("location","");
            y1=settings.getInt("y1",min);
            m1=settings.getInt("m1",min);
            d1=settings.getInt("d1",min);
            y2=settings.getInt("y2",min);
            m2=settings.getInt("m2",min);
            d2=settings.getInt("d2",min);
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.dateLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,location);
            max = gallery.size()-1;
            sz = gallery.size();
            date = true;
            locat = true;
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
            System.out.println("date location set");
        } else {
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.getAllimgGallerys();
            max = gallery.size()-1;
            sz=gallery.size();
        }
        image = (ImageView) findViewById(gallery);
        capt = (EditText) findViewById(R.id.caption);
        setFirst();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(gallery);
        Button camera  = (Button) findViewById(R.id.btn_capture);
        Button search = (Button) findViewById(R.id.search);
        prev = (Button) findViewById(R.id.btn_prev);
        nxt = (Button) findViewById(R.id.btn_nxt);
        Button s = (Button) findViewById(R.id.btn_s);
        Button exit = (Button) findViewById(R.id.btn_exit);
        Button l = (Button) findViewById(R.id.btn_location);

        search.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(MainActivity.this, com.example.ben.gallery.date.class));
                                       }
                                   });

        /*camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Capture.class));
            }

        });*/
        camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }*/
                startActivity(new Intent(MainActivity.this,Capture.class));

            }

        });
        nxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });

        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prev();
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,caption.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,location.class));
            }
        });


    }

    public void clear(){
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(MainActivity.this,MainActivity.class));

    }

    public void setFirst(){

        capt.setEnabled(false);
        DBHandler db = new DBHandler(this);
        List<imgGallery> gallery;
        if(date == true && caption == true && locat == true){
            gallery = db.dateCaptionLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && caption == true){
            gallery = db.dateCaption(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        }  else if(locat == true && caption == true){
            gallery = db.CaptionLocation(db.getAllimgGallerys(),cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && locat == true){
            gallery = db.dateLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true){
            gallery = db.date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("date: "+date);
        } else if (caption == true){
            System.out.println("WHAT IS CAP: "+cap);
            gallery = db.caption(db.getAllimgGallerys(),cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
        } else if(locat == true){
            gallery = db.location(db.getAllimgGallerys(),location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("location: "+locat);
        }  else {
            System.out.println("nothing");
            gallery = db.getAllimgGallerys();
            max = gallery.size()-1;
        }
        if( sz ==0){
        } else {
            image.setRotation((float) 90);
            image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
            capt.setText(gallery.get(current).getCaption());
            System.out.println("current: "+current);
            System.out.println("max: "+max);
            System.out.println("min: "+min);

        }
    }

    public void prev(){

        capt.setEnabled(false);
        DBHandler db = new DBHandler(this);
        List<imgGallery> gallery;
        if(date == true && caption == true && locat == true){
            gallery = db.dateCaptionLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && caption == true){
            gallery = db.dateCaption(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        }  else if(locat == true && caption == true){
            gallery = db.CaptionLocation(db.getAllimgGallerys(),cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && locat == true){
            gallery = db.dateLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true){
            gallery = db.date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("date: "+date);
        } else if (caption == true){
            System.out.println("WHAT IS CAP: "+cap);
            gallery = db.caption(db.getAllimgGallerys(),cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
        } else if(locat == true){
            gallery = db.location(db.getAllimgGallerys(),location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("location: "+locat);
        }  else {
            System.out.println("nothing");
            gallery = db.getAllimgGallerys();
            max = gallery.size()-1;
        }
        if( sz ==0){

        } else{
            if (current == min){

            } else if (current > min){
                current--;
                image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
                capt.setText(gallery.get(current).getCaption());
                System.out.println("current: "+current);
                System.out.println("max: "+max);
                System.out.println("min: "+min);
            }
        }
    }

    public void next(){

        capt.setEnabled(false);
        DBHandler db = new DBHandler(this);
        List<imgGallery> gallery;
        if(date == true && caption == true && locat == true){
            gallery = db.dateCaptionLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && caption == true){
            gallery = db.dateCaption(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        }  else if(locat == true && caption == true){
            gallery = db.CaptionLocation(db.getAllimgGallerys(),cap,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true && locat == true){
            gallery = db.dateLocation(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2,location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
            System.out.println("location: "+locat);
            System.out.println("date: "+date);
        } else if(date == true){
            gallery = db.date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("date: "+date);
        } else if (caption == true){
            System.out.println("WHAT IS CAP: "+cap);
            gallery = db.caption(db.getAllimgGallerys(),cap);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("caption: "+caption);
        } else if(locat == true){
            gallery = db.location(db.getAllimgGallerys(),location);
            max = gallery.size()-1;
            printDb(gallery);
            System.out.println("location: "+locat);
        }  else {
            System.out.println("nothing");
            gallery = db.getAllimgGallerys();
            max = gallery.size()-1;
        }
        if( sz ==0){

        } else{
            if (current == max){
            } else if (current < max){
                current++;
                image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
                capt.setText(gallery.get(current).getCaption());
                System.out.println("current: "+current);
                System.out.println("max: "+max);
                System.out.println("min: "+min);
            }
        }

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Bitmap getThumbnailBitmap(String path, int thumbnailSize) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1)) {
            return null;
        }
        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / thumbnailSize;
        return BitmapFactory.decodeFile(path, opts);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        uri = intent.getData();
        String url = getPath(uri);
        System.out.println("URL OF PICTURE: " + url );
        image.setImageBitmap(getThumbnailBitmap(url,300));
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nurl",url);
        editor.commit();
        startActivity(new Intent(MainActivity.this,Capture.class));
        }
    private String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null,null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    public void printDb(List<imgGallery> db ){
        List<imgGallery> gallery = db;
        for (imgGallery imgGallery : gallery) {
            String log = "Id: "+ imgGallery.getId() + " ,Location: " + imgGallery.getLocation() + " ,Caption: " + imgGallery.getCaption() + "Image path: " + imgGallery.getImage()+" ,Time: " + imgGallery.getYear()+"/"+imgGallery.getMonth()+"/"+imgGallery.getDay();
            Log.d("gallery: : ", log);
        }
    }
    }

