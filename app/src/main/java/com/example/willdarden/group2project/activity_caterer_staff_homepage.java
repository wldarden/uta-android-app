package com.example.willdarden.group2project;

/**
 * Created by Will Darden on 4/21/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activity_caterer_staff_homepage extends AppCompatActivity {

    Button GoToViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_staff_homepage);

        final String userName = getIntent().getStringExtra("LOGINID");
        TextView tv = (TextView)findViewById(R.id.tvUsername);
        tv.setText(userName);

        GoToViewEvents = findViewById(R.id.CatererStaffMyEventsBtn);
        GoToViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_caterer_staff_homepage.this, viewmyevents.class);
                String args = "mine";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", userName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
