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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.ben.gallery.R.id.gallery;

public class MainActivity extends AppCompatActivity {
    int min;
    int max;
    int current = 0;
    Button nxt;
    Button prev;
    ImageView image;
    EditText caption;
    boolean date;
    boolean cap;
    boolean location;
    int y1;
    int y2;
    int m1;
    int m2;
    int d1;
    int d2;
    Uri uri;
    int sz;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            image = (ImageView) findViewById(R.id.imagegallery);
            caption = (EditText) findViewById(R.id.caption);
            List<imgGallery> gallery = date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
            max = gallery.size()-1;
            sz=gallery.size();
            date = true;

        }  else {
            DBHandler db = new DBHandler(this);
            List<imgGallery> gallery = db.getAllimgGallerys();
            max = gallery.size()-1;
            sz=gallery.size();
        }
        setFirst();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(gallery);
        Button capture = (Button) findViewById(R.id.btn_capture);
        Button search = (Button) findViewById(R.id.search);
        prev = (Button) findViewById(R.id.btn_prev);
        nxt = (Button) findViewById(R.id.btn_nxt);
        Button s = (Button) findViewById(R.id.btn_s);
        Button exit = (Button) findViewById(R.id.btn_exit);

        search.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(MainActivity.this, com.example.ben.gallery.date.class));
                                       }
                                   });

        capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Capture.class));
            }

        });
        capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

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
                startActivity(new Intent(MainActivity.this,search.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    public void clear(){
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();
            finish();
            System.exit(0);
    }

    public void setFirst(){
        image = (ImageView) findViewById(gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        printDb();
        List<imgGallery> gallery;
        if(date == true){
            gallery = date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
        } else{
            gallery = db.getAllimgGallerys();
        }

        if( sz ==0){
        System.out.println("Database is empty");
        } else {
            System.out.println("IMAGE URL"+ gallery.get(0).getImage());
            image.setRotation((float) 90);
            image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
            caption.setText(gallery.get(current).getCaption());
        }
    }

    public void prev(){
        image = (ImageView) findViewById(gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        printDb();
        List<imgGallery> gallery;
        if(date == true){
            gallery = date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
        } else{
            gallery = db.getAllimgGallerys();
        }
        if( sz ==0){

        } else{
            System.out.println("max: "+max);
            System.out.println("CURRENT: " +current);
            if (current == min){

            } else if (current > min){
                current--;
                image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
                caption.setText(gallery.get(current).getCaption());

                System.out.println("CURRENT: " +current);
            }
        }
    }

    public void next(){
        image = (ImageView) findViewById(gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        List<imgGallery> gallery;
        if(date == true){
            gallery = date(db.getAllimgGallerys(),y1,y2,m1,m2,d1,d2);
        } else{
            gallery = db.getAllimgGallerys();
        }

        if( sz ==0){

        } else{
            System.out.println("max: "+max);
            System.out.println("CURRENT: " +current);
            if (current == max){
            } else if (current < max){
                current++;
                image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
                caption.setText(gallery.get(current).getCaption());

                System.out.println("CURRENT: " +current);

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

    public void printDb(){
        DBHandler db = new DBHandler(this);
        List<imgGallery> gallery = db.getAllimgGallerys();
        for (imgGallery imgGallery : gallery) {
            String log = "Id: "+ imgGallery.getId() + " ,Location: " + imgGallery.getLocation() + " ,Caption: " + imgGallery.getCaption() + "Image path: " + imgGallery.getImage()+" ,Time: " + imgGallery.getYear()+"/"+imgGallery.getMonth()+"/"+imgGallery.getDay();
            Log.d("gallery: : ", log);
        }
    }

    public List<imgGallery> date(List<imgGallery> g, int y1, int y2,int m1,int m2, int d1, int d2) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        DBHandler db = new DBHandler(this);
        //get all from the database
        List<imgGallery> gallery = g;
        List<imgGallery> filteredgallery = new ArrayList<imgGallery>();
        int i;
        for (i =0; i<=gallery.size()-1; i++){
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.YEAR, y1);
            cal.set(Calendar.MONTH, (m1-1));
            cal.set(Calendar.DAY_OF_MONTH, d1);
            String dat = f.format(cal.getTime());

            Calendar cal1 = Calendar.getInstance();
            cal1.clear();
            cal1.set(Calendar.YEAR, gallery.get(i).getYear());
            cal1.set(Calendar.MONTH, (gallery.get(i).getMonth()-1));
            cal1.set(Calendar.DAY_OF_MONTH, gallery.get(i).getDay());
            String dat1 = f.format(cal1.getTime());

            Calendar cal2 = Calendar.getInstance();
            cal2.clear();
            cal2.set(Calendar.YEAR, y2);
            cal2.set(Calendar.MONTH, (m2-1));
            cal2.set(Calendar.DAY_OF_MONTH, d2);
            String dat2 = f.format(cal2.getTime());


            if((((cal1)).compareTo((cal)) * (cal2).compareTo((cal1)) >= 0)){
                filteredgallery.add(new imgGallery((filteredgallery.size()+1),gallery.get(i).getLocation(),gallery.get(i).getCaption(),gallery.get(i).getImage(),gallery.get(i).getYear(),gallery.get(i).getMonth(),gallery.get(i).getDay()));
            } else {

            }
        }
        return filteredgallery;
    }

    /*public List<imgGallery> date(int y1, int y2,int m1,int m2, int d1, int d2) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        DBHandler db = new DBHandler(this);
        //get all from the database
        List<imgGallery> gallery = db.getAllimgGallerys();
        List<imgGallery> filteredgallery = new ArrayList<imgGallery>();
        int i;
        for (i =0; i<=gallery.size()-1; i++){
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.YEAR, y1);
            cal.set(Calendar.MONTH, (m1-1));
            cal.set(Calendar.DAY_OF_MONTH, d1);
            String dat = f.format(cal.getTime());

            Calendar cal1 = Calendar.getInstance();
            cal1.clear();
            cal1.set(Calendar.YEAR, gallery.get(i).getYear());
            cal1.set(Calendar.MONTH, (gallery.get(i).getMonth()-1));
            cal1.set(Calendar.DAY_OF_MONTH, gallery.get(i).getDay());
            String dat1 = f.format(cal1.getTime());
            //System.out.println(dat1);

            Calendar cal2 = Calendar.getInstance();
            cal2.clear();
            cal2.set(Calendar.YEAR, y2);
            cal2.set(Calendar.MONTH, (m2-1));
            cal2.set(Calendar.DAY_OF_MONTH, d2);
            String dat2 = f.format(cal2.getTime());
            //System.out.println(dat2);
            System.out.println("between: "+dat+"and"+dat1+"this"+dat2);


            if((((cal1)).compareTo((cal)) * (cal2).compareTo((cal1)) >= 0)){
                System.out.println(((cal1)).compareTo((cal)));
                System.out.println((cal2).compareTo((cal1)));
                System.out.println("added: "+gallery.get(i).getMonth()+"-"+gallery.get(i).getDay());
                filteredgallery.add(new imgGallery((filteredgallery.size()+1),gallery.get(i).getLocation(),gallery.get(i).getCaption(),gallery.get(i).getImage(),gallery.get(i).getYear(),gallery.get(i).getMonth(),gallery.get(i).getDay()));
                //System.out.println("ADDED DATE: "+filteredgallery.get(filteredgallery.size()).getMonth()+"-"+filteredgallery.get(filteredgallery.size()+1).getDay());
            } else {

            }
        }
        int d;
        System.out.println("SIZE: "+filteredgallery.size());
        for(d=0;i<=filteredgallery.size()-1; i++){
            System.out.println(filteredgallery.get(d).getYear()+"-"+filteredgallery.get(d).getMonth()+"-"+filteredgallery.get(d).getDay());
        }
        return filteredgallery;
    }*/

    }

