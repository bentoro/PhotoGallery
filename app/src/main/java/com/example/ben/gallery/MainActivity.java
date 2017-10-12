package com.example.ben.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;


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
    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.gallery);
        Button capture = (Button) findViewById(R.id.btn_capture);
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(MainActivity.this,startDate.class));
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
        Button s;
        s = (Button) findViewById(R.id.btn_s);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,search.class));
            }
        });
    }
}
