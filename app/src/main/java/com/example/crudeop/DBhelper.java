package com.example.crudeop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="user.db";

    public DBhelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase s) {
        s.execSQL("create table userdetails(name TEXT primary key,contact TEXT,dob TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase s, int i, int i1) {
        s.execSQL("drop table userdetails");


    }
    public Boolean insertuserdata(String name,String contact,String dob){
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);
        long result=DB.insert("userdetails",null,cv);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }


}
