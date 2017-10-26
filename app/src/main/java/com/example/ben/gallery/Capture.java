package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
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
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.ben.gallery.R.id.caption;

public class Capture extends AppCompatActivity {

    Uri uri;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        String url = settings.getString("nurl"," ");
        System.out.println(Calendar.getInstance().get(Calendar.YEAR)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        Button back = (Button) findViewById(R.id.btn_back);
        Button add = (Button) findViewById(R.id.btn_add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Capture.this,MainActivity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNew();
                startActivity(new Intent(Capture.this,MainActivity.class));
            }
        });
        Button camera = (Button) findViewById(R.id.btn_capture);
        camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }

        });

    }

    public void addNew() {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        EditText cap = (EditText) findViewById(caption);
        String caption = cap.getText().toString();
        DBHandler db = new DBHandler(this);
        //SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        //String url = settings.getString("nurl"," ");
        System.out.println(url);
        String address="";
        try{
            double lng = 0;
            double lat = 0;
            ExifInterface exif = new ExifInterface(url);
            //lat = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            //longtitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            float[] LatLong = new float[2];
            if(exif.getLatLong(LatLong)){
                lat = LatLong[0];
                lng = LatLong[1];
            }
            System.out.println(lat);
            System.out.println(lng);
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat,lng,1);
            address = addresses.get(0).getAddressLine(0);
            System.out.println(address);

        } catch (IOException e) {
            Log.e("PictureActivity", e.getLocalizedMessage());
        }

        System.out.println(Calendar.getInstance().get(Calendar.YEAR)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        image.setImageBitmap(getThumbnailBitmap(url, 300));
        System.out.println("URL OF IMAGE: " + url);
        int y = Calendar.getInstance().get(Calendar.YEAR);
        int m = Calendar.getInstance().get(Calendar.MONTH)+1;
        int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        db.addimgGallery(new imgGallery(1,address,caption,url,y,m,d));
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    protected static final String KEY_IMAGE_DATA = "data";

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        ImageView image = (ImageView) findViewById(R.id.imageView);
        uri = data.getData();
        url = getPath(uri);
        System.out.println("URL OF PICTURE: " + url );
        image.setImageBitmap(getThumbnailBitmap(url,300));
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap imageBitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            image.setRotation((float) 90);
            image.setImageBitmap(imageBitmap);
        }
    }
    private String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null,null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

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
}
