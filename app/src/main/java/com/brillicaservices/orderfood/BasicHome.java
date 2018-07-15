package com.brillicaservices.orderfood;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.brillicaservices.orderfood.Common.Common;

import info.hoang8f.widget.FButton;

public class BasicHome extends AppCompatActivity {
    TextView fruit;
    TextView Name;
    FloatingActionButton btncart1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_home);
        fruit=(TextView) findViewById(R.id.fruit);
        Name=(TextView)findViewById(R.id.namey);
        Name.setText(Common.currentuser.getName());
        btncart1=(FloatingActionButton) findViewById(R.id.btnCart1);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BasicHome.this,Home2.class);
                startActivity(intent);
            }
        });
        btncart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BasicHome.this,Cart.class);
                startActivity(intent);
            }
        });

    }
}
