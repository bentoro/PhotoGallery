package com.example.ben.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button nxt;
    Button prev;
    ImageView image;
    EditText caption;
    String[] img;
    String[] cap;
    String[] time;
    String[] location;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        /*ArrayList arraylist = new ArrayList();
        ObjectSerializer serial = new ObjectSerializer();
        arraylist.add(new imgGallery("totoro","hello","Richmond","09/20/17"));

        SharedPreferences prefs = getSharedPreferences("User",0);
        SharedPreferences.Editor editor = prefs.edit();
        try{
            editor.putString("arraylist",serial.serialize(arraylist));
        } catch (IOException e){
            e.printStackTrace();
        }
        editor.commit();*/
    }
    /*private void save(){
        ObjectSerializer serial1 = new ObjectSerializer();
        ArrayList list= new ArrayList();

        SharedPreferences pref = getSharedPreferences("User",0);
        try{
            list = (ArrayList) serial1.deserialize((pref.getString("list", serial1.serialize(new ArrayList()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.gallery);
        Button capture = (Button) findViewById(R.id.capture);
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(MainActivity.this,searchDate.class));
                                       }
                                   });
        nxt = (Button) findViewById(R.id.btn_nxt);
        nxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.totoro);
                caption = (EditText) findViewById(R.id.caption);
                caption.setText("Totoro");
            }

        });
        capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Capture.class));
            }

        });
        /*capture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });*/

        prev = (Button) findViewById(R.id.btn_prev);
        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.asdf);
                caption = (EditText) findViewById(R.id.caption);
                caption.setText("asdf");
            }
        });
    }
}
