package com.example.siyuxiu.cateringapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;



public class CaterHomepage extends AppCompatActivity implements View.OnClickListener {


    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
DatabaseHelper db =new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        //插入数据
/*
        DatabaseHelper dbh=new DatabaseHelper(this);
        dbh.getWritableDatabase();
        dbh.insertContact_Cater("1","2","2018-12-11" , "12:15:00","3","lunch","Chinese","Formal","standard","Arlington","50","6","Siyu");
        dbh.insertContact_Cater("3","2","2018-11-11" , "11:15:00","3","lunch","Chinese","Formal","standard",null,"50","6","Siyu");
        dbh.insertContact_Cater("2","2","2018-10-11" , "10:15:00","3","lunch","Chinese","Formal","standard",null,"50","6","Siyu");
        dbh.insertContact_Cater("5","2","2018-12-11" , "13:15:00","3","lunch","Chinese","Formal","standard",null,"50","6","Siyu");
        dbh.close();
*/
        setContentView(R.layout.activity_cater_homepage);
        mButton1 = (Button) findViewById(R.id.caterer_event_list_homepage_button);
        mButton2 = (Button) findViewById(R.id.caterer_function_list_button);
        mButton3 = (Button) findViewById(R.id.logout_homepage_button);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        db.getReadableDatabase();

    }


    public void onClick(View v)
    {
        String str;
        switch (v.getId())
        {
            case R.id.caterer_event_list_homepage_button:
                str="1";





                Intent intent=new Intent(CaterHomepage.this,EventNameEnterPage.class);
                startActivity(intent);
                break;
            case R.id.caterer_function_list_button:
                str="1";
                Intent intent1 = new Intent(CaterHomepage.this,CatererFuctionList.class);
                startActivity(intent1);
                break;

        }

    }
}

