package com.example.willdarden.group2project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    private static final String TABLE_CREATE = "CREATE TABLE USERCREDENTIALS (LoginId text not null primary key, "+
            "Username text not null , "+
            "UserRole text not null , "+
            "EmailId text not null , "+
            "PhoneNumber text not null , "+
            "Password text not null);";

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

    private static final String USER_TABLE_NAME = "EVENTREQUESTS";
    private static final String COL1_EventName = "EventName";
    private static final String COL2_PartySize = "PartySize";
    private static final String COL3_EventDate = "EventDate";
    private static final String COL4_EventTime = "EventTime";
    private static final String COL5_EventDuration = "EventDuration";
    private static final String COL6_MealType = "MealType";
    private static final String COL7_MealVenue = "MealVenue";
    private static final String COL8_MealFormality = "MealFormality";
    private static final String COL9_DrinkVenue = "DrinkVenue";
    private static final String COL10_Venue = "Venue";
    private static final String COL11_Cost = "Cost";
    private static final String COL12_Staff = "Staff";
    private static final String COL13_Username = "Username";
    private static final String EVENTREQUESTS_CREATE = "CREATE TABLE EVENTREQUESTS (EventName text not null primary key, "+
            "PartySize number not null , "+
            "EventDate text not null , "+
            "EventTime text not null , "+
            "EventDuration number not null , "+
            "MealType text, "+
            "MealVenue text, "+
            "MealFormality text, "+
            "DrinkVenue text, "+
            "Venue text, "+
            "Cost number, "+
            "Staff text, "+
            "Username text not null);";

    private SQLiteDatabase sqliteDB;

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        sqLiteDatabase.execSQL(EVENTREQUESTS_CREATE);
        this.sqliteDB = sqLiteDatabase;
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
    //Caterer Find Specific Event by entering event name
    public Cursor findeventinfo(String data){
        sqliteDB = this.getWritableDatabase();
        String sql = "SELECT EventName, PartySize ,EventDate,EventTime ,EventDuration,MealType ,MealVenue,MealFormality,DrinkVenue,Venue,Cost,Staff FROM  EVENTREQUESTS" + " WHERE EventName = '"+data+"'";
        Cursor  cur = sqliteDB.rawQuery(sql,null);
        return  cur;
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
        checkQuery = "DROP TABLE IF EXISTS "+USER_TABLE_NAME;
        sqLiteDatabase.execSQL(checkQuery);
        this.onCreate(sqLiteDatabase);
    }

    public String searchPassword(String LoginId , String UserRole){
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT LoginId,UserRole,Password FROM USERCREDENTIALS;";
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

    public boolean insertContact(String loginid , String username , String role , String email , String phone , String password){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1_LoginId , loginid);
        values.put(COL2_UserName , username);
        values.put(COL3_UserRole , role);
        values.put(COL4_EmailId , email);
        values.put(COL5_PhoneNumbr , phone);
        values.put(COL6_Password , password);
        long insertResult = sqliteDB.insert(TABLE_NAME , null , values);
        if(insertResult == -1){return false;}
        else {return true;}
    }

    //    public boolean insertEvent(String EventName , String PartySize , String EventDate , String EventTime , String EventDuration , String MealType , String MealVenue , String MealFormality , String DrinkVenue , String Username){
    public boolean insertEvent(String EventName , String PartySize , String EventDate , String EventTime , int EventDuration , String MealType , String MealVenue , String MealFormality , String DrinkVenue , String Username){
        sqliteDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1_EventName , EventName);
        values.put(COL2_PartySize , PartySize);
        values.put(COL3_EventDate, EventDate);
        values.put(COL4_EventTime , EventTime);
        values.put(COL5_EventDuration , EventDuration);
        values.put(COL6_MealType , MealType);
        values.put(COL7_MealVenue , MealVenue);
        values.put(COL8_MealFormality, MealFormality);
        values.put(COL9_DrinkVenue , DrinkVenue);
        values.put(COL13_Username , Username);
        long insertResult = sqliteDB.insert(USER_TABLE_NAME , null , values);
        if(insertResult == -1){return false;}
        else {return true;}
    }

    public String[] getmyEvents(String getuser){
        sqliteDB = this.getReadableDatabase();
        String[] params = new String [] {getuser};
        String fetchQuery = "SELECT * FROM EVENTREQUESTS WHERE Username = ?;";
        Cursor cursor = sqliteDB.rawQuery(fetchQuery , params);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("EventName")));
            // names.add(cursor.getString(cursor.getColumnIndex("PartySize")));
            names.add(cursor.getString(cursor.getColumnIndex("EventDate")));
            names.add(cursor.getString(cursor.getColumnIndex("EventTime")));
          /*  names.add(cursor.getString(cursor.getColumnIndex("EventDuration")));
            names.add(cursor.getString(cursor.getColumnIndex("MealType")));
            names.add(cursor.getString(cursor.getColumnIndex("MealVenue")));
            names.add(cursor.getString(cursor.getColumnIndex("MealFormality")));
            names.add(cursor.getString(cursor.getColumnIndex("Venue")));
            names.add(cursor.getString(cursor.getColumnIndex("Cost")));
            names.add(cursor.getString(cursor.getColumnIndex("Staff")));
            names.add(cursor.getString(cursor.getColumnIndex("Username"))); */
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("Data: ",  Arrays.toString(new ArrayList[]{names}));
        return names.toArray(new String[names.size()]);
    }

    public String[] getAllEvents(){
        sqliteDB = this.getReadableDatabase();
        String fetchQuery = "SELECT * FROM EVENTREQUESTS;";
        Cursor cursor = sqliteDB.rawQuery(fetchQuery , null);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("EventName")));
            // names.add(cursor.getString(cursor.getColumnIndex("PartySize")));
            names.add(cursor.getString(cursor.getColumnIndex("EventDate")));
            names.add(cursor.getString(cursor.getColumnIndex("EventTime")));
          /*  names.add(cursor.getString(cursor.getColumnIndex("EventDuration")));
            names.add(cursor.getString(cursor.getColumnIndex("MealType")));
            names.add(cursor.getString(cursor.getColumnIndex("MealVenue")));
            names.add(cursor.getString(cursor.getColumnIndex("MealFormality")));
            names.add(cursor.getString(cursor.getColumnIndex("Venue")));
            names.add(cursor.getString(cursor.getColumnIndex("Cost")));
            names.add(cursor.getString(cursor.getColumnIndex("Staff")));
            names.add(cursor.getString(cursor.getColumnIndex("Username"))); */
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("Data: ",  Arrays.toString(new ArrayList[]{names}));
        return names.toArray(new String[names.size()]);
    }

    public String[] getEventSummary(String eventtitle){
        sqliteDB = this.getReadableDatabase();
        String[] params = new String [] {eventtitle};
        String fetchQuery = "SELECT * FROM EVENTREQUESTS WHERE EventName = ?";
        Cursor cursor = sqliteDB.rawQuery(fetchQuery , params);
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("EventName")));
            names.add(cursor.getString(cursor.getColumnIndex("PartySize")));
            names.add(cursor.getString(cursor.getColumnIndex("EventDate")));
            names.add(cursor.getString(cursor.getColumnIndex("EventTime")));
            names.add(cursor.getString(cursor.getColumnIndex("EventDuration")));
            names.add(cursor.getString(cursor.getColumnIndex("MealType")));
            names.add(cursor.getString(cursor.getColumnIndex("MealVenue")));
            names.add(cursor.getString(cursor.getColumnIndex("MealFormality")));
            names.add(cursor.getString(cursor.getColumnIndex("DrinkVenue")));
            names.add(cursor.getString(cursor.getColumnIndex("Venue")));
            names.add(cursor.getString(cursor.getColumnIndex("Username")));
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("Data: ",  Arrays.toString(new ArrayList[]{names}));
        return names.toArray(new String[names.size()]);
    }

    public boolean cancelEvent(String eventtitle){
        //  return sqliteDB.delete(USER_TABLE_NAME, COL1_EventName + "=" + eventtitle, null) > 0;
        sqliteDB = this.getReadableDatabase();
        String[] params = new String [] {eventtitle};
        Log.d("To be deleted:", eventtitle);
        //String deleteQuery = "DELETE FROM EVENTREQUESTS WHERE EventName = ?";
        //Cursor cursor = sqliteDB.rawQuery(deleteQuery , params);
        //int query_output = sqliteDB.delete(fetchQuery, COL1_EventName , params);
//        int query_output = sqliteDB.delete(USER_TABLE_NAME, COL1_EventName + " = ? AND" + COL13_Username + " = ?" , params);
        int query_output = sqliteDB.delete(USER_TABLE_NAME, COL1_EventName + " = ?" , params);
        if (query_output == -1){
            return false;
        }
        else{
            return true;
        }
//        return true;
    }

    public ArrayList<ArrayList> getEvents (){
        ArrayList<String> list = new ArrayList<>();
        // String getQuery = "SELECT EventName,EventDate,EventTime FROM EVENTREQUESTS;";
        String getQuery = "SELECT * FROM EVENTREQUESTS;";
        //try {
        sqliteDB = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = sqliteDB.rawQuery(getQuery , null);

        if(!cursor.moveToFirst())
            return (ArrayList) list;
        do {
                 /*   viewmyevents vme = new viewmyevents();
                    vme.eventnamedisp = cursor.getString(0);
                    vme.partysizedisp = cursor.getString(1);
                    vme.eventdatedisp = cursor.getString(2);
                    vme.eventtimedisp = cursor.getString(3);
                    vme.eventdurationdisp = cursor.getString(4);
                    vme.mealtypedisp = cursor.getString(5);
                    vme.mealvenuedisp = cursor.getString(6);
                    vme.mealformalitydisp = cursor.getString(7);
                    vme.drinkvenuedisp = cursor.getString(8);
                    vme.venuedisp = cursor.getString(9);
                    vme.costdisp = cursor.getString(10);
                    vme.staffdisp = cursor.getString(11);
                    vme.usernamedisp = cursor.getString(12);

                    Log.d("Data: ",  vme.eventnamedisp
                            + " " + vme.partysizedisp
                            + " " + vme.eventdatedisp
                            + " " + vme.eventtimedisp
                            + " " + vme.eventdurationdisp
                            + " " + vme.mealtypedisp
                            + " " + vme.mealvenuedisp
                            + " " + vme.mealformalitydisp
                            + " " + vme.drinkvenuedisp
                            + " " + vme.venuedisp
                            + " " + vme.costdisp
                            + " " + vme.staffdisp
                            + " " + vme.usernamedisp
                            + "\n");
                    */
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            list.add(cursor.getString(5));
            list.add(cursor.getString(6));
            list.add(cursor.getString(7));
            list.add(cursor.getString(8));
            list.add(cursor.getString(9));
            list.add(cursor.getString(10));
            list.add(cursor.getString(11));
            list.add(cursor.getString(12));

            Log.d("Data: ", String.valueOf(list));
        }while(cursor.moveToNext());
        cursor.close();
        sqliteDB.close();
        return (ArrayList) list;
        //  }
        //  finally {
        //  sqliteDB.close();
        //}
    }

}