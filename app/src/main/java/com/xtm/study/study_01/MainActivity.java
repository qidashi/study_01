package com.xtm.study.study_01;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insertData(View view) {
        ContentValues values = new ContentValues();
        values.put("name","lisi");
        values.put("age",12);
        values.put("sex","girl");
        getContentResolver().insert(Uri.parse("content://com.xtm.study.provider/person"),values);
    }
}
