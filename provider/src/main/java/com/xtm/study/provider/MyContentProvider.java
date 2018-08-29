package com.xtm.study.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Function:
 * Created by TianMing.Xiong on 18-8-29.
 */

public class MyContentProvider extends ContentProvider {

    private static String strAuthorities = "com.xtm.study.provider";
    private SQLiteDatabase mDatabase;
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        Log.e("MyContentProvider", "onCreate*********创建数据库*******");
        // 第一步：创建匹配规则，匹配上返回函数addURI(path,code)第二个参数的code值
        initMatcher();
        // 第二步：创建持久化数据库（包括file，xml,sp,SQLite等）
        MySQLiteOpenHelper mSQLiteOpenHelper = new MySQLiteOpenHelper(getContext());
        mDatabase = mSQLiteOpenHelper.getWritableDatabase();
        return true;
    }
    private void initMatcher() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(strAuthorities,"person",1);// 匹配表
        uriMatcher.addURI(strAuthorities,"person/#",2);// 匹配表下的某个字段的一条记录,#为通配符
    }

    // 第三步：筛选外部连接过来的数据(通过第一步的参数二来判别)
    private boolean isMatch(Uri uri){
        switch (uriMatcher.match(uri)){
            case 1:
                return true;
            case 2:
                return true;
            default:
                Log.e("isMatch","match failed ...");
                break;
        }
        return false;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.e("MyContentProvider", "getType****************"+uri);
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (isMatch(uri)) {
            Log.e("insert","isMath...............");
            mDatabase.insert("person",null,values);
            getContext().getContentResolver().notifyChange(uri,null);
            return uri;
        }else {
            Log.e("insert","isNotMath...............");
        }
//        ContentUris.withAppendedId(uri,id);// 为路径加上ID
//        long id = ContentUris.parseId(uri);// 获取路径后面的ID
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if(isMatch(uri)){
            Log.e("delete","isMath...............");
            int affectedLine = mDatabase.delete("person",selection, selectionArgs);
            return affectedLine;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
       if(isMatch(uri)){
           Log.e("update","isMath...............");
           int affectedLine = mDatabase.update("person", values, selection, selectionArgs);
           return affectedLine;
       }
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if(isMatch(uri)){
            Log.e("query","isMath...............");
            Cursor cursor = mDatabase.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
            return cursor;
        }
        return null;
    }
}
