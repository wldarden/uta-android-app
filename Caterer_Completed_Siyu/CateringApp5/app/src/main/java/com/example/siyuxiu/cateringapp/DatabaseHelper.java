package com.example.siyuxiu.cateringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Date;

/**
 * Created by priya on 3/16/2018.
 */
/**
 * Created by Siyu Xiu on 4/21/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CateringAppDB.db";
    private static final String TABLE_NAME = "USERCREDENTIALS";
    private static final String COL1_LoginId = "LoginId";
    private static final String COL2_UserName = "Username";
    private static final String COL3_UserRole = "UserRole";
    private static final String COL4_EmailId = "EmailId";
    private static final String COL5_PhoneNumbr = "PhoneNumber";
    private static final String COL6_Password = "Password";
    private static final String COL7_Status = "Status";



// caterer part start

    private static final String Cater1_EventName = "EventName";
    private static final String Cater2_PartySize = "PartySize";
    private static final String Cater3_EventDate = "EventDate";
    private static final String Cater4_EventTime = "EventTime";
    private static final String Cater5_EventDuration = "EventDuration";
    private static final String Cater6_MealType = "MealType";
    private static final String Cater7_MealVenue = "MealVenue";
    private static final String Cater8_MealFormality = "MealFormality";
    private static final String Cater9_DrinkVenue = "DrinkVenue";
    private static final String Cater10_Venue = "Venue";
    private static final String Cater11_Cost = "Cost";
    private static final String Cater12_Staff = "Staff";
    private static final String Cater13_Username = "Username";
// caterer part end

    private static final String TABLE_CREATE = "CREATE TABLE USERCREDENTIALS (LoginId text not null primary key, "+
            "Username text not null, "+
            "UserRole text not null , "+
            "EmailId text  not null, "+
            "PhoneNumber text not null, "+
            "Password text not null , "+
            "Status text);";//I added this attribute here

    // move " SQLiteDatabase sqliteDB;" to line 77 (according to Lin's DatabaseHelper)

// Create Caterer Table
    private static final String EVENTREQUESTS_CREATE = "CREATE TABLE EVENTREQUESTS" +
            " (EventName text not null primary key, "+
            "PartySize number not null , "+
            "EventDate text not null , "+
            "EventTime time not null , "+
            "EventDuration number not null , "+
            "MealType text, "+
            "MealVenue text, "+
            "MealFormality text, "+
            "DrinkVenue text, "+
            "Venue text, "+
            "Cost number, "+
            "Staff text, "+
            "Username text not null);";

    SQLiteDatabase sqliteDB;


    public DatabaseHelper (Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        // create Caterer Table
        sqLiteDatabase.execSQL(EVENTREQUESTS_CREATE);
        this.sqliteDB = sqLiteDatabase;
    }

    public boolean updateHallName(String EventName,String HallName){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
          values.put(Cater10_Venue, HallName);


        int i = sqliteDB.update("EVENTREQUESTS",values,"EventName=?",new String[]{EventName});
        if(i==1){
            return true;
        }else{
            return false;
        }

    }


    public boolean updateStaffNumber(String EventName,String StaffNum){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cater12_Staff, StaffNum);


        int i = sqliteDB.update("EVENTREQUESTS",values,"EventName=?",new String[]{EventName});
        if(i==1){
            return true;
        }else{
            return false;
        }

    }
////////////////////////////有问题
    public boolean CatererCreateEvent(String EventName,String eventTime){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cater12_Staff, eventTime);


        int i = sqliteDB.update("EVENTREQUESTS",values,"EventName=?",new String[]{EventName});
        if(i==1){
            return true;
        }else{
            return false;
        }

    }

    public boolean CatererAddResources(String EventName, String MealType , String FoodVenue, String MealFormality, String DrinkVenue){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(Cater1_EventName , EventName);
        values.put(Cater6_MealType,MealType);
        values.put(Cater7_MealVenue,FoodVenue);
        values.put(Cater8_MealFormality,MealFormality);
        values.put(Cater9_DrinkVenue,DrinkVenue);


        int i = sqliteDB.update("EVENTREQUESTS",values,"EventName=?",new String[]{EventName});
        if(i==1){
            return true;
        }else{
            return false;
        }
    }



    public long insertContact_Cater(String EventName, String PartySize ,String EventDate , String EventTime ,String EventDuration
            , String MealType , String MealVenue, String MealFormality, String DrinkVenue, String Venue, String Cost, String Staff , String Username){

        ContentValues values = new ContentValues();

        values.put(Cater1_EventName , EventName);
        values.put(Cater2_PartySize, PartySize);
        values.put(Cater3_EventDate, EventDate);
        values.put(Cater4_EventTime, EventTime);
        values.put(Cater5_EventDuration,EventDuration);
        values.put(Cater6_MealType,MealType);
        values.put(Cater7_MealVenue,MealVenue);
        values.put(Cater8_MealFormality,MealFormality);
        values.put(Cater9_DrinkVenue,DrinkVenue);
        values.put(Cater10_Venue,Venue);
        values.put(Cater11_Cost,Cost);
        values.put(Cater12_Staff,Staff);
        values.put(Cater13_Username,Username);



        long insertResult = sqliteDB.insert("EVENTREQUESTS" , null , values);
        //if(insertResult == -1){return false;}
        //else {return true;}
        return insertResult;
    }


//Caterer Find Specific Event by entering event name
    public Cursor findeventinfo(String data){
        sqliteDB = this.getWritableDatabase();
        String sql = "SELECT EventName, PartySize ,EventDate,EventTime ,EventDuration,MealType ,MealVenue,MealFormality,DrinkVenue,Venue,Cost,Staff FROM  EVENTREQUESTS" + " WHERE EventName = '"+data+"'";
        Cursor  cur = sqliteDB.rawQuery(sql,null);
        return  cur;
    }


    public Cursor findeventinfo_Calendar(String data){
        sqliteDB = this.getWritableDatabase();
        String sql = "SELECT EventName, EventTime, Venue FROM  EVENTREQUESTS" + " WHERE EventDate = '"+data+"'";
        Cursor  cur = sqliteDB.rawQuery(sql,null);
        return  cur;
    }
    public void DeleteEvent(String id){
        sqliteDB = this.getWritableDatabase();
        sqliteDB.delete("EVENTREQUESTS", "EventName=?", new String[]{id});

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String checkQuery = "DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(checkQuery);
        this.onCreate(sqLiteDatabase);
    }


    public Cursor FetchRegisRequest(){
        sqliteDB = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor  cur = sqliteDB.rawQuery(sql,null);
        return  cur;

    }

    public Cursor FetchUserInfo(){
        sqliteDB = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Status = 'Approved'";
        Cursor cur  = sqliteDB.rawQuery(sql,null);
        return  cur;
    }

    public void deleteUser(String id){
        sqliteDB = this.getWritableDatabase();
        sqliteDB.delete(TABLE_NAME, "LoginID=?", new String[]{id});

    }

    public boolean UpdateUserInfo(String id,int where , String content) {
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (where) {
            case 1:values.put(COL2_UserName, content);break;

            case 2:values.put(COL3_UserRole, content);break;
            case 3:values.put(COL4_EmailId, content);break;
            case 4:values.put(COL5_PhoneNumbr, content);break;
            case 5:values.put(COL6_Password, content);break;
            default:return false;
        }


        int i = sqliteDB.update(TABLE_NAME,values,"LoginId=?",new String[]{id});
        if(i==1){
            return true;
        }else{
            return false;
        }

    }

    public boolean updateRegisrequest(String id,boolean result){
        sqliteDB = this.getWritableDatabase();
        String IfAppro;
        if(result){
            IfAppro = "Approved";
        }else{
            IfAppro = "Declined";
        }
        ContentValues values = new ContentValues();
        values.put(COL7_Status,IfAppro);


        String sql = "UPDATE "+ TABLE_NAME +" SET "+COL7_Status +" = "+IfAppro+" WHERE "+COL1_LoginId+" = "+id;
        int i = sqliteDB.update(TABLE_NAME,values,COL1_LoginId+"=?", new String[]{id});
        if(i == 1){
            return true;
        }else{
            return false;
        }


    }

    public boolean insertContact(String loginid , String username , String role , String email , String phone , String password){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1_LoginId , loginid);
        values.put(COL2_UserName , username);
        values.put(COL3_UserRole , role);
        values.put(COL4_EmailId , email);
        values.put(COL5_PhoneNumbr , phone);
        values.put(COL6_Password , password);
        //whenever we create a new record of a system user, the initial value of status is always "null", until an admin reviews it


        long insertResult = sqliteDB.insert(TABLE_NAME , null , values);
        if(insertResult == -1){return false;}
        else {return true;}
    }





    public String searchPassword(String LoginId , String UserRole){
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT LoginId,UserRole,Password FROM USERCREDENTIALS WHERE Status = 'Approved';";//we only care about the records which has been approved
        Cursor curs = sqliteDB.rawQuery(fetchQuery , null);
        String LoginidChk , RoleChk , PasswordChk;
        PasswordChk = "not found";
        if(curs.moveToFirst()){
            do {
                LoginidChk = curs.getString(0);
                RoleChk = curs.getString(1);
                if(LoginidChk.equals(LoginId) && RoleChk.equals(UserRole)){
                    PasswordChk = curs.getString(2);
                    break;
                }
            }while(curs.moveToNext());
        }
        return PasswordChk;
    }


}
