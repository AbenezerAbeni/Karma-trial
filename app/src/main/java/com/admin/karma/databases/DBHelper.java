package com.admin.karma.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.admin.karma.MainActivity;
import com.admin.karma.MyRecyclerViewAdapter;
import com.admin.karma.SavedItem;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 16/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dagus";
    private static final String TABLE_Bookmarks = "first";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BLOG = "blogs_id";
    private static final String KEY_CONTENT = "contents";
    private static final String KEY_IMAGES = "images";


    private int blogid;
    private String blogtit;
    Context mContext;
    SQLiteDatabase db;
    private static final String TAG = "DBHelper";


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TableItems.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_Bookmarks);
        db.execSQL(TableItems.DROP_TABLE);

        // Create tables again
        onCreate(db);

    }

    // code to add the new saves
    public void addSaves(SavedItem saves) {
        String dos = saves.getArticle_Title();
       boolean ab = false;

       ab = hasObject(dos);
      //  Log.d(TAG, String.format("%d records found"," aM HERE AT aB BOOLEAN object" ));

        if( ab != true){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_BLOG, saves.getArticle_Id()); // Contact Name
            values.put(KEY_TITLE, saves.getArticle_Title()); // Contact Phone
            // values.put(KEY_CONTENT, saves.getArticle_Content());
            //  values.put(KEY_IMAGES, saves.getArticle_Image());
            // Inserting Row
            db.insert(TableItems.TABLE_Bookmarks, null, values);

            //2nd argument is String containing nullColumnHack
            db.close(); // Closing database connection
            Log.d("Inserting 000000", "Inserting Wait wait here --------------------------");
      //  Toast.makeText(MyRecyclerViewAdapter.this, "Saved Successfully  " , Toast.LENGTH_SHORT).show();


        }



    }

    public boolean hasObject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TableItems.TABLE_Bookmarks+ " WHERE " + KEY_TITLE + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {id});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }


    public List<SavedItem> getAllSaved() {
        List<SavedItem> savesList = new ArrayList<SavedItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TableItems.TABLE_Bookmarks;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SavedItem saves = new SavedItem();
                saves.setArticle_Id(Integer.parseInt(cursor.getString(1)));
                saves.setArticle_Title(cursor.getString(2));
               // saves.setArticle_Content(cursor.getString(3));
                // Adding Save to list
                savesList.add(saves);
            } while (cursor.moveToNext());
        }

        // return Save list
        return savesList;
    }

    public List<SavedItem> getBlogsidonly() {
        List<SavedItem> saves2 = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  blogs_id FROM " + TableItems.TABLE_Bookmarks;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SavedItem saves = new SavedItem();
                saves.setArticle_Id(Integer.parseInt(cursor.getString(0)));
//                saves.setArticle_Title(cursor.getString(2));
//                // saves.setArticle_Content(cursor.getString(3));
                // Adding Save to list
                saves2.add(saves);
            } while (cursor.moveToNext());
        }

        // return Save list
        return saves2;
    }
    public int updateSaves (SavedItem saves) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BLOG, saves.getArticle_Id());
        values.put(KEY_TITLE, saves.getArticle_Title());

        // updating row
        return db.update(TableItems.TABLE_Bookmarks, values, KEY_BLOG + " = ?",
                new String[] { String.valueOf(saves.getArticle_Id()) });
    }

    // Getting Saves Count
    public int getSavedCount() {

        String countQuery = "SELECT  blogs_id  FROM " + TableItems.TABLE_Bookmarks;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dos = cursor.getCount();
        cursor.close();

        // return count
        return dos;
    }

    public void OnCheckBlogs(List<SavedItem> cn , int id , String tit) {
        blogid = id;
        blogtit = tit;

        cn = new ArrayList <SavedItem> ();
        for(int i= 0 ; i< cn.size(); i++){

            if( blogid != cn.indexOf(i) ){
                addSaves(new SavedItem(blogid,blogtit));
            } else
                Toast.makeText(mContext, "BookMark is Saved Already ..", Toast.LENGTH_SHORT).show();
            //go to the thrid section of the app and try to highlight the required blogid
        }
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }



}
