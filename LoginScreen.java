package com.example.priya.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);
    String userRoleSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Spinner spin = (Spinner)findViewById(R.id.spinnerUserRoleLoginScreen);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.UserRoleDropDown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.btnLoginLoginScreen){
            EditText loginid , password , role ;
            loginid = (EditText)findViewById(R.id.etLoginIdLoginScreen);
            password = (EditText)findViewById(R.id.etPasswordLoginScreen);
            //role = (EditText)findViewById(R.id.etRoleLoginScreen);
            //role.setKeyListener(null);
            String LoginId_Str = loginid.getText().toString();
            String Password_Str = password.getText().toString();
            //String Role_Str = role.getText().toString();

            String PasswordVar = helper.searchPassword(LoginId_Str , userRoleSelected);
            if(Password_Str.equals(PasswordVar)) {
                Intent intent = new Intent(LoginScreen.this, admin_home_screen.class);
                intent.putExtra("LOGINID", LoginId_Str);
                startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(LoginScreen.this , "Incorrect Login, Password or User Role" , Toast.LENGTH_LONG);
                toast.show();
            }
        }

        if(view.getId() == R.id.btnSignUpLoginScreen){
            Intent intent = new Intent(LoginScreen.this, activity_registration_screen.class);
            startActivity(intent);

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        userRoleSelected = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
