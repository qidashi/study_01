package com.xtm.study.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Function:
 * Created by TianMing.Xiong on 18-8-29.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String name="db_person.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    private String sql = "create table person( _id integer primary key autoincrement, name text, age integer, sex text )";

    public MySQLiteOpenHelper(Context context) {
        /**
         * @param context to use to open or create the database
         * @param name of the database file, or null for an in-memory database
         * @param factory to use for creating cursor objects, or null for the default
         * @param version number of the database (starting at 1); if the database is older,
         *     {@link #onUpgrade} will be used to upgrade the database; if the database is
         *     newer, {@link #onDowngrade} will be used to downgrade the database
         */
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
