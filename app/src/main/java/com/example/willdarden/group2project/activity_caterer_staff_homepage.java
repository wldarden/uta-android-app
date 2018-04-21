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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activity_caterer_staff_homepage extends AppCompatActivity {

    public void onButtonClick(View view){
        if(view.getId() == R.id.CatererStaffMyEventsBtn){
            Intent intent = new Intent(this, activity_event_list.class);
//            intent.putExtra("LOGINID", LoginId_Str);
            startActivity(intent);
        }

        if(view.getId() == R.id.LogoutBtn){
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);

        }

    }


}
