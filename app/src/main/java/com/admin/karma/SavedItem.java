package com.admin.karma;

import android.database.Cursor;

/**
 * Created by Admin on 23/11/2017.
 */

public class SavedItem {

    private String article_Title ;
    private CharSequence article_Content;
    private byte [] article_Image;
    private int article_Id;

    public SavedItem (int id, String name, CharSequence _contents ){
        this.article_Id = id;
        this.article_Title = name;
        this.article_Content = _contents;
        //this.article_Image =ime;
    }

    public SavedItem(int id, String tit) {
        this.article_Id = id;
        this.article_Title = tit;
    }

    public SavedItem() {

    }

    public String getArticle_Title() {
        return article_Title;
    }

    public void setArticle_Title(String article_title) {
        this.article_Title = article_title;
    }

    public CharSequence getArticle_Content() {return article_Content;}

    public void setArticle_Content (CharSequence article_content) {this.article_Content = article_content;}

    public byte[] getArticle_Image() {return article_Image;}

    public void setArticle_Image (byte[] article_image) {this.article_Image = article_image;}

    public int getArticle_Id() {return article_Id;}

    public void setArticle_Id (int article_id) {this.article_Id = article_id;}

    public static SavedItem fromCursor(Cursor cursor) {
        //TODO return your MyListItem from cursor.

        SavedItem listItem = new SavedItem();
        listItem.setArticle_Title(cursor.getString(cursor.getColumnIndex("title")));
        return listItem;
      //  return fromCursor(cursor);
    }
}
