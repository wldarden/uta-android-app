package com.example.willdarden.group2project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class admin_home_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);

        String userName = getIntent().getStringExtra("LOGINID");
        TextView tv = (TextView)findViewById(R.id.tvUsername);
        tv.setText(userName);
    }
}