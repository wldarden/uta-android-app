package com.example.willdarden.group2project;

/**
 * Created by Will Darden on 4/21/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activity_caterer_staff_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_staff_homepage);

        String userName = getIntent().getStringExtra("LOGINID");
        TextView tv = (TextView)findViewById(R.id.tvUsername);
        tv.setText(userName);
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.CatererStaffMyEventsBtn){
            Intent intent = new Intent(this, activity_event_list.class);
//            intent.putExtra("LOGINID", userName);
            startActivity(intent);
        }

        if(view.getId() == R.id.LogoutBtn){
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);

        }

    }


}
