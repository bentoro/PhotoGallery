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

public class MainActivity extends AppCompatActivity {
    int min;
    int max;
    Button nxt;
    Button prev;
    ImageView image;
    EditText caption;
    Uri uri;
    int current = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFirst();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.gallery);
        Button capture = (Button) findViewById(R.id.btn_capture);
        Button search = (Button) findViewById(R.id.search);
        prev = (Button) findViewById(R.id.btn_prev);
        nxt = (Button) findViewById(R.id.btn_nxt);
        Button s = (Button) findViewById(R.id.btn_s);
        //image.setImageURI(u);

        search.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(MainActivity.this,startDate.class));
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
    }

    public void setFirst(){
        image = (ImageView) findViewById(R.id.gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        printDb();
        List<imgGallery> gallery = db.getAllimgGallerys();
        max = gallery.size()-1;
        if( gallery.size() ==0){
        System.out.println("Database is empty");
        } else {
            System.out.println("IMAGE URL"+ gallery.get(0).getImage());
            image.setRotation((float) 90);
            image.setImageBitmap(getThumbnailBitmap(gallery.get(current).getImage(), 400));
            caption.setText(gallery.get(current).getCaption());
        }
    }

    public void prev(){
        image = (ImageView) findViewById(R.id.gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        printDb();
        List<imgGallery> gallery = db.getAllimgGallerys();
        max = gallery.size()-1;
        if( gallery.size() ==0){

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
        image = (ImageView) findViewById(R.id.gallery);
        caption = (EditText) findViewById(R.id.caption);
        caption.setEnabled(false);
        DBHandler db = new DBHandler(this);
        printDb();
        //get all from the database
        List<imgGallery> gallery = db.getAllimgGallerys();
        max = gallery.size()-1;
        if( gallery.size() ==0){

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

    }

