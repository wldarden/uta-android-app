package com.example.siyuxiu.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatererFuctionList extends AppCompatActivity implements View.OnClickListener {


    private Button mButton2;

    DatabaseHelper db =new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_fuction_list);
       // mButton1 = (Button) findViewById(R.id.CatererCreateEvent_button);
        mButton2 = (Button) findViewById(R.id.CatererViewEventCalendar_button);


        //mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        db.getReadableDatabase();
    }

    public void onClick(View v)
    {
        String str;
        switch (v.getId())
        {
            case R.id.CatererViewEventCalendar_button:
                str="1";
                Intent intent1 = new Intent(CatererFuctionList.this,CatererPickUpDatePage.class);
                startActivity(intent1);
                break;

        }



    }


}
