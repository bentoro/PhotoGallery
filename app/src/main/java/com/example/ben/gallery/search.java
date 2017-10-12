package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class search extends AppCompatActivity {

    Button nxt;
    Button prev;
    EditText caption;
    ImageView image;

    int max;
    int min = 0;
    int current = 0;
    int y1;
    int y2;
    int m1;
    int m2;
    int d1;
    int d2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        y1=settings.getInt("y1",min);
        m1=settings.getInt("m1",min);
        d1=settings.getInt("d1",min);
        y2=settings.getInt("y2",min);
        m2=settings.getInt("m2",min);
        d2=settings.getInt("d2",min);
        image = (ImageView) findViewById(R.id.imagegallery);
        caption = (EditText) findViewById(R.id.caption);
        List<imgGallery> gallery1 = date(y1,y2,m1,m2,d1,d2);
        max = gallery1.size()-1;
        addListenerOnButton();
        setFirst();

    }

    public List<imgGallery> date(int y1, int y2,int m1,int m2, int d1, int d2) {
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
                filteredgallery.add(new imgGallery((filteredgallery.size()+1),gallery.get(i).getImage(),gallery.get(i).getYear(),gallery.get(i).getMonth(),gallery.get(i).getDay()));
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
    }

    public void setFirst(){
        List<imgGallery> gallery1 = date(y1,y2,m1,m2,d1,d2);
        if( gallery1.size() ==0){

        } else {
            String img = (gallery1.get(current).getImage());
            String uri = "drawable/"+img;
            image.setImageResource(getResources().getIdentifier(uri,"drawable",getPackageName()));
            caption.setText(gallery1.get(current).getYear()+"-"+gallery1.get(current).getMonth()+"-"+gallery1.get(current).getDay());

        }


    }

    public void prev(){
        List<imgGallery> gallery1 = date(y1,y2,m1,m2,d1,d2);
        if( gallery1.size() ==0){

        } else{
            System.out.println("max: "+max);
            System.out.println("CURRENT: " +current);
            if (current == min){

            } else if (current > min){
                current--;
                String img = (gallery1.get(current).getImage());
                String uri = "drawable/"+img;
                image.setImageResource(getResources().getIdentifier(uri,"drawable",getPackageName()));
                caption.setText(gallery1.get(current).getYear()+"-"+gallery1.get(current).getMonth()+"-"+gallery1.get(current).getDay());

                System.out.println("CURRENT: " +current);
            }
        }
    }

    public void next(){

        List<imgGallery> gallery1 = date(y1,y2,m1,m2,d1,d2);
        if( gallery1.size() ==0){

        } else{
            System.out.println("max: "+max);
            System.out.println("CURRENT: " +current);
            if (current == max){
            } else if (current < max){
                current++;
                String img = (gallery1.get(current).getImage());
                String uri = "drawable/"+img;
                image.setImageResource(getResources().getIdentifier(uri,"drawable",getPackageName()));
                caption.setText(gallery1.get(current).getYear()+"-"+gallery1.get(current).getMonth()+"-"+gallery1.get(current).getDay());

                System.out.println("CURRENT: " +current);

            }
        }

    }

    public void addListenerOnButton() {
        nxt = (Button) findViewById(R.id.btn_nxt);
        nxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }

        });


        prev = (Button) findViewById(R.id.btn_prev);
        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prev();
            }
        });

        Button capture = (Button) findViewById(R.id.btn_capture);
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(search.this,startDate.class));
            }
        });

        Button fb = (Button) findViewById(R.id.btn_fb);
        fb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(search.this,Facebook.class));
            }
        });

        capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(search.this,Capture.class));
            }

        });
    }
}
