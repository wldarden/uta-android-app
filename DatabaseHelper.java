package com.example.priya.cateringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by priya on 3/16/2018.
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
    private static final String TABLE_CREATE = "CREATE TABLE USERCREDENTIALS (LoginId text not null primary key, "+
                                                "Username text not null , "+
                                                "UserRole text not null , "+
                                                "EmailId text not null , "+
                                                "PhoneNumber text not null , "+
                                                "Password text not null);";

    SQLiteDatabase sqliteDB;

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqliteDB = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String checkQuery = "DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(checkQuery);
        this.onCreate(sqLiteDatabase);
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


}
