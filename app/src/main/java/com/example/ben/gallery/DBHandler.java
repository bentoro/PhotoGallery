package com.example.ben.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 10;
    // Database Name
    private static final String DATABASE_NAME = "gallery";
    // Contacts table name
    private static final String TABLE_GALLERY = "IMAGE";
    // imgGallerys Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CAPTION = "caption";
    private static final String KEY_IMAGE = "name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GALLERY_TABLE = "CREATE TABLE " + TABLE_GALLERY + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION + " TEXT, " + KEY_CAPTION + " TEXT, " + KEY_IMAGE + " TEXT," + KEY_YEAR + " INTEGER,"+ KEY_MONTH + " INTEGER," + KEY_DAY + " INTEGER" + ")";
        db.execSQL(CREATE_GALLERY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        // Creating tables again
        onCreate(db);
    }
    // Adding new imgGallery
    public void addimgGallery(imgGallery imgGallery) {
        //OPENING DATABASE
        SQLiteDatabase db = this.getWritableDatabase();
        //MAPS TABLES COLUMNS
        ContentValues values = new ContentValues();
        values.put(KEY_LOCATION, imgGallery.getLocation()); // imgGallery Name
        values.put(KEY_CAPTION, imgGallery.getCaption()); // imgGallery Name
        values.put(KEY_IMAGE, imgGallery.getImage()); // imgGallery Name
        values.put(KEY_YEAR, imgGallery.getYear()); // imgGallery Phone Number
        values.put(KEY_MONTH, imgGallery.getMonth()); // imgGallery Phone Number
        values.put(KEY_DAY, imgGallery.getDay()); // imgGallery Phone Number
        // Inserting Row
        db.insert(TABLE_GALLERY, null, values);
        db.close(); // Closing database connection
    }

    // Getting All imgGallerys
    public List<imgGallery> getAllimgGallerys() {
        List<imgGallery> imgGalleryList = new ArrayList<imgGallery>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_GALLERY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                imgGallery imgGallery = new imgGallery();
                imgGallery.setId(Integer.parseInt(cursor.getString(0)));
                imgGallery.setLocation(cursor.getString(1));
                imgGallery.setCaption(cursor.getString(2));
                imgGallery.setImage(cursor.getString(3));
                imgGallery.setYear(Integer.parseInt(cursor.getString(4)));
                imgGallery.setMonth(Integer.parseInt(cursor.getString(5)));
                imgGallery.setDay(Integer.parseInt(cursor.getString(6)));
            // Adding Gallery to the list
                imgGalleryList.add(imgGallery);
            } while (cursor.moveToNext());
        }
        // return Gallery
        return imgGalleryList;
    }

    // Getting imgGallerys Count
    public int getimgGallerysCount(int y1, int y2,int m1,int m2, int d1, int d2) {
        String countQuery = "SELECT COUNT (*) FROM " + TABLE_GALLERY+ " WHERE ("+KEY_YEAR+" BETWEEN " + y1+" AND "+y2+") AND ("
                +KEY_MONTH+" BETWEEN " + m1 + " AND " + m2 + ") AND ("
                +KEY_DAY+" BETWEEN " + d1 + " AND " + d2 + ") ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        System.out.println("COUNT: " +count);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating a imgGallery
    public int updateimgGallery(imgGallery imgGallery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOCATION, imgGallery.getLocation());
        values.put(KEY_CAPTION, imgGallery.getCaption());
        values.put(KEY_IMAGE, imgGallery.getImage());
        values.put(KEY_YEAR, imgGallery.getYear());
        values.put(KEY_MONTH, imgGallery.getMonth());
        values.put(KEY_DAY, imgGallery.getDay());
        // updating row
        return db.update(TABLE_GALLERY, values, KEY_ID + " = ?",
        new String[]{String.valueOf(imgGallery.getId())});
    }
    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        db.close();

    }

    public List<imgGallery> date(int y1, int y2,int m1,int m2, int d1, int d2) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //get all from the database
        List<imgGallery> gallery = getAllimgGallerys();
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
}