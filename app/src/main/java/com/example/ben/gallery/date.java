package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class date extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
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
        calender = (CalendarView) findViewById(R.id.strtCal);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView calendarView, int year, int months, int day) {
                EditText start = (EditText) findViewById(R.id.strt_txt);
                start.setText(year+"-"+(months+1)+"-"+day);

            }
        });

        CalendarView calender1;
        calender1 = (CalendarView) findViewById(R.id.endCal);

        calender1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView calendarView, int year, int months, int day) {
                EditText end = (EditText) findViewById(R.id.end_txt);
                end.setText(year+"-"+(months+1)+"-"+day);
                System.out.println("PUT END DATE "+ year+"-" +(months+1)+"-" +day);

            }
        });

    }

    public void addListenerOnButton() {
        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(date.this,MainActivity.class));
                }
        });

        Button end = (Button) findViewById(R.id.btn_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(date.this, MainActivity.class);
                EditText start = (EditText) findViewById(R.id.strt_txt);
                String datstart = start.getText().toString();
                String[] startDate = datstart.split("-");
                SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                System.out.println("PUT START DATE "+ startDate[0]+"-" +startDate[1]+"-" +startDate[2]);
                editor.putInt("y1",Integer.parseInt(startDate[0]));
                editor.putInt("m1",Integer.parseInt(startDate[1]));
                editor.putInt("d1",Integer.parseInt(startDate[2]));
                EditText end = (EditText) findViewById(R.id.end_txt);
                String dateend = end.getText().toString();
                String[] endDate = dateend.split("-");
                System.out.println("PUT END DATE "+ endDate[0]+"-" +endDate[1]+"-" +endDate[2]);
                editor.putInt("y2",Integer.parseInt(endDate[0]));
                editor.putInt("m2",Integer.parseInt(endDate[1]));
                editor.putInt("d2",Integer.parseInt(endDate[2]));
                editor.commit();
                startActivity(send);
            }
        });
    }
}
