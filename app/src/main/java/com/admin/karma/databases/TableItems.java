package com.admin.karma.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 17/12/2017.
 */

public class TableItems {

    public static final String NAME = TableItems.class.getSimpleName().toLowerCase();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dagus";
    public static final String TABLE_Bookmarks = "first";
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "contents";
    public static final String KEY_IMAGES = "images";
    public static final String KEY_BLOG = "blogs_id";


    private int blogid;
    private String blogtit;
    Context mContext;
    SQLiteDatabase db;

   public static final String CREATE_TABLE  = "CREATE TABLE " + TABLE_Bookmarks + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ KEY_BLOG + " INTEGER UNIQUE , "+ KEY_TITLE + " TEXT,"
            + KEY_CONTENT + " TEXT," + KEY_IMAGES + " BLOB " + ")";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAME;
    public static String[] Columns = new String[]{ KEY_TITLE};
}
