package com.example.ben.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        btnListener();
    }


    public void btnListener(){
        Button search = (Button) findViewById(R.id.btn_search);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                EditText caption = (EditText) findViewById(R.id.txt_location);
                String cap = caption.getText().toString().toLowerCase();
                editor.putString("location", cap);
                editor.commit();
                startActivity(new Intent(location.this,MainActivity.class));
            }
        });
    }
}
