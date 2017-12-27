package com.admin.karma;

/**
 * Created by Abenezer Abebe on 10/25/16.
 */

public class FeedItem {

    private int ids;
    private String title;
    private String thumbnail;
    private CharSequence content;

    public int getids() {
        return ids;
    }

    public void setIds (int id) {
        this.ids = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

}
