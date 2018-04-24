package com.example.siyuxiu.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CatererEventCreation extends AppCompatActivity implements View.OnClickListener {
    private Button Create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_event_creation);




        Create = (Button)findViewById(R.id.EventCreation_Create_button);
        Create.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        String str;
        DatabaseHelper dbh3=new DatabaseHelper(this);
        switch (view.getId())
        {
            case R.id.EventCreation_Create_button:
                str="1";
                dbh3.getWritableDatabase();


                TextView tv1;
                tv1 = (TextView)findViewById(R.id.Scheduleplace_hallname_plaintext);

                Intent intent5 = getIntent();
                String data_EventName = intent5.getStringExtra("Event_Name_ToSchedulepage");
                String data_HallName = tv1.getText().toString();

                dbh3.updateHallName(data_EventName,data_HallName);

                Toast.makeText(CatererEventCreation.this,"Hall "+data_HallName+" is assigned",Toast.LENGTH_LONG).show();
                break;





        }

    }
}
