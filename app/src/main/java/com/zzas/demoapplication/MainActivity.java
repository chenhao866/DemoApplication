package com.zzas.demoapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;
import com.zzas.chen.SDsqlist.DatabaseContext;
import com.zzas.chen.SDsqlist.SdCardDBHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Bt_hello;
    private Boolean isScceed = false;
    private  int Index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bt_hello = findViewById(R.id.Bt_hello);
        Bt_hello.setOnClickListener(this);

    }

    //扫描二维码后的回调函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            String strName=data.getStringExtra("text"); // 显示识别到的文字
            SetSQLData(strName);//将二维码插入数据表
        }
    }

    //将二维码插入数据表
    private void SetSQLData(final String strName){
        new Thread(new Runnable(){
            @Override
            public void run() {
            SdCardDBHelper sdCardDBHelper = new SdCardDBHelper(MainActivity.this);
                sdCardDBHelper.saveUserinfo(MainActivity.this,strName);//将二维码插入数据表
                isScceed = SpinnerHandler.post(mUpdateResults);
            }
        }).start();
    }

    private Handler SpinnerHandler = new Handler();
    private Runnable mUpdateResults = new Runnable() {
        public void run(){
            if(isScceed){
                Toast.makeText(getApplicationContext(), "第"+Index+"条数据成功", Toast.LENGTH_SHORT).show();
                Index++;
            }else {
                Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this, Main2Activity.class), 1);
    }
}
