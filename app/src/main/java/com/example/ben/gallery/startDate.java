package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.List;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class startDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_date);
        addListenerOnButton();

        //DBHandler db = new DBHandler(this);

        //db.delete();
// Inserting Shop/Rows
        //Log.d("Insert: ", "Inserting ..");
        //db.updateimgGallery(new imgGallery(1,"totoro", "5-04-17"));
        //db.updateimgGallery(new imgGallery(3,"server", "10-04-17"));
        //db.addimgGallery(new imgGallery(3,"server", "09/04/17"));
        //db.addimgGallery(new imgGallery(2,"Dunkin Donuts", "White Plains, NY 10601"));
        //db.addimgGallery(new imgGallery(3,"Pizza Porlar", "North West Avenue, Boston , USA"));
        //db.addimgGallery(new imgGallery(4,"Town Bakers", "Beverly Hills, CA 90210, USA"));

// Reading all shops
        //Log.d("Reading: ", "BETWEEN");
        //List<imgGallery> gallery = db.getAllimgGallerys();
        /*List<imgGallery> gallery1 = db.date("1-04-17","2-04-17");

        for (imgGallery imgGallery : gallery1) {
            String log = "Id: " + imgGallery.getId() + " ,Name: " + imgGallery.getImage() + " ,Time: " + imgGallery.getTime();
// Writing shops to log
            Log.d("gallery: : ", log);
        }*/

        /*Log.d("Reading: ", "Reading all gallery");
        List<imgGallery> gallery = db.getAllimgGallerys();
        //List<imgGallery> gallery = db.date("07/04/17","10/04/17");

        for (imgGallery imgGallery : gallery) {
            String log = "Id: " + imgGallery.getId() + " ,Name: " + imgGallery.getImage() + " ,Time: " + imgGallery.getTime();
// Writing shops to log
            Log.d("gallery: : ", log);
        }*/
        CalendarView calender;
        calender = (CalendarView) findViewById(R.id.calendarView);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView calendarView, int year, int months, int day) {
                EditText start = (EditText) findViewById(R.id.startdate);
                start.setText(year+"-"+(months)+"-"+day);

            }
        });

    }

    public void addListenerOnButton() {
        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startDate.this,MainActivity.class));
                }
        });

        Button end = (Button) findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(startDate.this, endDate.class);
                EditText start = (EditText) findViewById(R.id.startdate);
                String d = start.getText().toString();
                String[] dd = d.split("-");
                System.out.println("PUT START DATE "+ dd[0]+"-" +dd[1]+"-" +dd[2]);
                SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("y1",Integer.parseInt(dd[0]));
                editor.putInt("m1",Integer.parseInt(dd[1]));
                editor.putInt("d1",Integer.parseInt(dd[2]));
                editor.commit();
                System.out.println("PUT START DATE "+ dd[0]+"-" +dd[1]+"-" +dd[2]);
                startActivity(send);
            }
        });
    }
}
