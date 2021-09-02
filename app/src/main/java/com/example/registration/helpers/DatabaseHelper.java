package com.example.registration.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.registration.models.UserDataModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PhoneNo";
    public static final String COL_5 = "ICON";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "EMAIL TEXT," +
                "PHONENO TEXT," +
                "ICON TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String email,String phone,String icon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,icon);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<UserDataModel> getAllUsers() {
        ArrayList<UserDataModel> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserDataModel userDataModel = new UserDataModel();
                userDataModel.setUserName(cursor.getString(1));
                userDataModel.setUserEmail(cursor.getString(2));
                userDataModel.setUserPhoneNo(cursor.getString(3));
                userDataModel.setUserIcon(Integer.parseInt(cursor.getString(4)));
                arrayList.add(userDataModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
}
