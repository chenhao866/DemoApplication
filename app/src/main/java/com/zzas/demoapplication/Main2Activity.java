package com.zzas.demoapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;
import com.zzas.chen.SDsqlist.DatabaseContext;
import com.zzas.chen.SDsqlist.SdCardDBHelper;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main2Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mZXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZXingScannerView = new ZXingScannerView(this); // 将ZXingScannerView作为布局
        setContentView(mZXingScannerView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mZXingScannerView.setResultHandler(this); // 设置处理结果回调
        mZXingScannerView.startCamera(); // 打开摄像头
    }

    @Override
    protected void onPause() {
        super.onPause();
        mZXingScannerView.stopCamera(); // 活动失去焦点的时候关闭摄像头
    }

    @Override
    public void handleResult(Result result) { // 实现回调接口，将数据回传并结束活动
        Intent data = new Intent();
        data.putExtra("text", result.getText());
        setResult(RESULT_OK, data);
        finish();
    }















//    private void mysun(){
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                DatabaseContext dbContext = new DatabaseContext(MainActivity.this);
//                SdCardDBHelper dbHelper = new SdCardDBHelper(dbContext);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();// 取得数据库操作
//                dbHelper.saveUserinfo(db);
//            }
//        }).start();
//    }
}
