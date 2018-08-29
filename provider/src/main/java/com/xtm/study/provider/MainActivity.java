package com.xtm.study.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertData(View view) {
        ContentValues values = new ContentValues();
        values.put("name","zhangsan");
        values.put("age",11);
        values.put("sex","man");
        getContentResolver().insert(Uri.parse("content://com.xtm.study.provider/person"),values);
    }


    public void deleteData(View view) {
        getContentResolver().delete(Uri.parse("content://com.xtm.study.provider/person"),"_id=?",new String[]{"1"});
    }

    public void updateData(View view) {
        ContentValues values = new ContentValues();
        values.put("name","sanxiao");
        values.put("age",27);
        values.put("sex","man");
        getContentResolver().update(Uri.parse("content://com.xtm.study.provider/person"),values,"_id=?",new String[]{"3"});
    }

    public void queryData(View view) {
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.xtm.study.provider/person"), null, null, null, null);
       if(null!=cursor){
           while (cursor.moveToNext()){
               String name = cursor.getString(cursor.getColumnIndex("name"));
               Log.e("name:","name:"+name);
           }
       }

    }
}
