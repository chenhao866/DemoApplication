package com.zzas.chen.SDsqlist;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zzas.demoapplication.MainActivity;

public class SdCardDBHelper extends SQLiteOpenHelper {

    public static final String TAG = "SdCardDBHelper";
    public static String DATABASE_NAME = "sddb.db";
    public static int DATABASE_VERSION = 1;

    public SdCardDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 插入记录
    public Boolean saveUserinfo(Context context,String name) {
        try {
            DatabaseContext dbContext = new DatabaseContext(context);
            SdCardDBHelper dbHelper = new SdCardDBHelper(dbContext);
            SQLiteDatabase db = dbHelper.getWritableDatabase();// 取得数据库操作
            db.execSQL("insert into user (role,name) values(?,?)", new String[] {"#",name});
            db.close();// 记得关闭数据库操作
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "开始创建数据库表");
        try{
            //创建用户表(user)
            db.execSQL("create table if not exists user" +
                    "(_id integer primary key autoincrement,role varchar(10),name varchar(20))");
            Log.e(TAG, "创建离线所需数据库表成功");
        }
        catch(SQLException se){
            se.printStackTrace();
            Log.e(TAG, "创建离线所需数据库表失败");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
