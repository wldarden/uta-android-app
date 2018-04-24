package com.example.siyuxiu.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CatererPickUpDatePage extends AppCompatActivity implements View.OnClickListener {


    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_pick_up_date_page);
        submit = (Button)findViewById(R.id.DatePickUpSubmmit_button);
        submit.setOnClickListener(this);

    }


    public void onClick(View v)
    {
        String str;

        switch (v.getId())
        {
            case R.id.DatePickUpSubmmit_button:
                str="1";
                TextView tv;
                tv = (TextView)findViewById(R.id.CatererDatePickUp_plainText);

                String date_data = tv.getText().toString();
                Intent intent=new Intent(CatererPickUpDatePage.this,CatererCalendarEventView.class);

                intent.putExtra("Event_Date", date_data);
                startActivity(intent);
                break;


        }

    }
}
