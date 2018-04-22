package com.example.siyuxiu.cateringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventNameEnterPage extends AppCompatActivity  implements View.OnClickListener  {


    private Button createDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_name_enter_page);
        createDatabase = (Button)findViewById(R.id.gobutton);
        createDatabase.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        String str;

        switch (v.getId())
        {
            case R.id.gobutton:
                str="1";
                TextView tv;
                tv = (TextView)findViewById(R.id.EnterEventName_enterpage_plaintext);

                String data = tv.getText().toString();
                Intent intent=new Intent(EventNameEnterPage.this,CatererEventList.class);
                intent.putExtra("Event_Name", data);
                startActivity(intent);
                break;

        }

    }

}

