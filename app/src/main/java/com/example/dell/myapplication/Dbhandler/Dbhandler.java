package com.example.dell.myapplication.Dbhandler;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.myapplication.Activitybo;
import com.example.dell.myapplication.Memo;
import com.example.dell.myapplication.loginBO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 5/21/2016.
 */
public class Dbhandler extends SQLiteOpenHelper {
    private static final String TAG = "DBHandler";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "STOCKS";

    public Dbhandler() {
        super(Memo.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static void deleteDatabase() {
        Memo.getContext().deleteDatabase(DATABASE_NAME);
    }
    public void onOpen(SQLiteDatabase db) { db.setForeignKeyConstraintsEnabled(true);}
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    @Override public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            createT_MSTR_User(db);
            create_MSTR_Activity(db);
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        { Log.d(TAG, ex.getMessage()); }
        finally { db.endTransaction(); }
    }
    private void createT_MSTR_User(SQLiteDatabase db) {
        final String TABLE_NAME = "User";
        final String Col_Id = "Id";
        final String Col_UserName = "UserName";
        final String Col_Name = "Name";
        final String Col_Passward = "Passward";
        final String Col_Isactive = "Isactive";
        final String Col_ISAdmin = "ISAdmin";
        final String Col_IsSession = "IsOn";
        String CREATE_Config_table = "CREATE TABLE " + TABLE_NAME + "(" + Col_Id + " INTEGER PRIMARY KEY NOT NULL," + Col_UserName + " TEXT," + Col_Name + " TEXT,"+ Col_Passward + " TEXT,"+Col_Isactive + " BIT,"+Col_IsSession + " BIT,"+Col_ISAdmin + " BIT"+")";
        db.execSQL(CREATE_Config_table);
    }

    private void create_MSTR_Activity(SQLiteDatabase db) {
        final String TABLE_NAME = "Activity";
        final String Col_Id = "Id";
        final String Col_UserName = "Description";
        final String Col_Name = "Location";
        final String Col_Passward = "mDate";
        final String Col_Isactive = "mTime";
        final String Col_ISAdmin = "UserName";
        String CREATE_Config_table = "CREATE TABLE " + TABLE_NAME + "(" + Col_Id + " INTEGER PRIMARY KEY NOT NULL," + Col_UserName + " TEXT," + Col_Name + " TEXT,"+ Col_Passward + " TEXT,"+Col_Isactive + " TEXT,"+Col_ISAdmin + " TEXT"+")";
        db.execSQL(CREATE_Config_table);
    }
//    private void create_MSTR_Activity(SQLiteDatabase db) {
//        final String TABLE_NAME = "Activity";
//        final String Col_Id = "Id";
//        final String Col_Description = "Description";
//        final String Col_Location = "Location";
//        final String Col_Date = "mDate";
//        final String Col_Time = "mTime";
//        final String Col_UName = "UserName";
//
//        String CREATE_Config_table = "CREATE TABLE " + TABLE_NAME + "(" + Col_Id + " INTEGER PRIMARY KEY NOT NULL," + Col_Description + " TEXT," + Col_Location + " TEXT,"+ Col_Date + " TEXT,"+Col_Time +"TEXT,"+Col_UName+"TEXT"+")";
//        db.execSQL(CREATE_Config_table);
//    }


    public boolean getUserName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select UserName from User where UserName='" +name+"'", null);
            if(cursor.moveToFirst()) {
                return true;
            }
        } finally { if(cursor != null) cursor.close(); }
        return false;
    }
    public void insertUserDetails(loginBO loginBO) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Name", loginBO.getName());
            values.put("UserName", loginBO.getUserName());
            values.put("Passward", loginBO.getPassword());
            values.put("Isactive",Boolean.TRUE);
            values.put("ISAdmin",Boolean.FALSE);
            values.put("IsOn",Boolean.FALSE);
            db.insertOrThrow("User", null, values);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
    }
    public void insertActivityDetails(Activitybo activitybo) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Description", activitybo.getDescription());
            values.put("Location", activitybo.getLocation());
            values.put("mDate", activitybo.getDate());
            values.put("mTime",activitybo.getTime());
            values.put("UserName",activitybo.getUname());

            db.insertOrThrow("Activity", null, values);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
    }
    public void updateUserSession(String Uname ) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("IsOn", Boolean.TRUE);
            db.update("User", values, "UserName='" + Uname + "'", null); // updating row
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
    }
    public void updateUserLogout(String Uname ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IsOn", Boolean.FALSE);
        db.update("User", values, "UserName='" + Uname + "'", null); // updating row
    }
    public String getisOnline()
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select UserName from User where IsOn=1", null);
            if(cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex("UserName"));

            }
        }  finally { if(cursor != null)

            cursor.close(); }
        return "000";

    }
    public boolean getLoggedin()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select UserName from User where IsOn=1", null);
            if(cursor.moveToFirst()) {
                return true;
            }
        } finally { if(cursor != null) cursor.close(); }
        return false;
    }
    public void insertMASTERUserDetails(SQLiteDatabase db) {
        try {

            ContentValues values = new ContentValues();
            values.put("Name", "ADMIN");
            values.put("UserName", "admin");
            values.put("Passward", "admin");
            values.put("Isactive",Boolean.TRUE);
            values.put("ISAdmin",Boolean.TRUE);
            values.put("IsOn",Boolean.FALSE);
            db.insertOrThrow("User", null, values);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
    }
    public boolean getAuthorizin(String Name,String Pass )
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select UserName,Passward from User where UserName='" +Name+"' and Passward='"+Pass+"'", null);
            if(cursor.moveToFirst()) {
                return true;
            }
        } finally { if(cursor != null) cursor.close(); }
        return false;
    }
    public List<Activitybo> SelectAllData(String uname) {
        SQLiteDatabase db = getReadableDatabase();
        List<Activitybo> contactList = new ArrayList<Activitybo>();
        Cursor c = null;
        try {
            c = db.rawQuery("select Description,Location,mDate,mTime from Activity where UserName='" +uname+"'", null);
            while (c.moveToNext()) {
                Activitybo contact = new Activitybo();
                contact.setDescription(c.getString(0));
                contact.setLocation(c.getString(1));
                contact.setDate(c.getString(2));
                contact.setTime(c.getString(3));
                contactList.add(contact);
            }
        } finally { if(c != null) c.close(); }
        return contactList;
    }
}