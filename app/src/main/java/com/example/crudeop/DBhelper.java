package com.example.crudeop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public Boolean updateuserdata(String name,String contact,String dob){
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("contact",contact);
        cv.put("dob",dob);
        Cursor cur= DB.rawQuery("select * from userdetails where name=?",new String[]{name});
        if(cur.getCount()>0){
            long result = DB.update("userdetails",cv,"name=?",new String[]{name});
            if (result == -1){
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }
    public Boolean deleteuserdata(String name){
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cur= DB.rawQuery("select * from userdetails where name=?",new String[]{name});
        if(cur.getCount()>0){
            long result = DB.delete("userdetails","name=?",new String[]{name});
            if (result == -1){
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from userdetails",null);
        return cursor;
    }



}
