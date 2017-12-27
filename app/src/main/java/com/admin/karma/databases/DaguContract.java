package com.admin.karma.databases;

/**
 * Created by Admin on 17/12/2017.
 */
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DaguContract
{
    public static final String CONTENT_AUTHORITY = "com.admin.karma.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //Tables specific path:
    public static final String RELATIVE_DAGU_URI = "todo";

    public static class Todo
    {
        // URI for the table
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath
                (RELATIVE_DAGU_URI).build();

        // Entire table
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.admin" + "" +
                ".karma.provider.todo";
        // Single row within the table
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.admin"
                + ".karma.provider.todo";

        // Table name
     //   public static final String TABLE_NAME = "todo";
        private static final String TABLE_Bookmarks = "first";


        // Define table columns
        public interface Columns extends BaseColumns
        {
//            String TITLE = "title";
//            String DESCRIPTION = "description";
//            String DATE = "date";
//            String PRIORITY = "priority";

            String KEY_ID = "id";
            String KEY_TITLE = "title";
            String KEY_BLOG = "blogs_id";
            String KEY_CONTENT = "contents";
            String KEY_IMAGES = "images";
        }

        public static Uri buildRowUri(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
