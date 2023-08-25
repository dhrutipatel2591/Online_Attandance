package com.example.onlineattendancesystem1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassHelper {
    Context context = null;
    SQLiteDatabase OAS = null;

    public class dbhelper extends SQLiteOpenHelper {
        public dbhelper(Context context){
            super(context,"OAS",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table Class(ClassId INTEGER PRIMARY KEY AUTOINCREMENT,ClassName text,Subject text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }
    public ClassHelper(Context context){
        this.context = context;
    }
    public void opendb(){
        OAS= new dbhelper(context).getWritableDatabase();
    }
    public void closedb(){
        OAS.close();
    }
    public  long insert(String classname, String subjectname) {
        ContentValues cv = new ContentValues();
        cv.put("ClassName",classname);
        cv.put("SubjectName",subjectname);
        return OAS.insert("Class",null,cv);
    }
}
