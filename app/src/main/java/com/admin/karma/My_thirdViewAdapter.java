package com.admin.karma;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.karma.adapters.CursorRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 23/11/2017.
 */

public class My_thirdViewAdapter extends CursorRecyclerViewAdapter<My_thirdViewAdapter.MyViewHolder> {

    private List<SavedItem> savedItems ;
    private Context mContext;
    Cursor mCursor;

    public My_thirdViewAdapter(Context context,Cursor cursor){
        super(context,cursor);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_third ,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }



    @Override
    public int getItemCount() {
       // return (null != savedItems ? savedItems.size() : 0);

        return mCursor.getCount();
      //  return savedItems.size();

    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, Cursor cursor) {

        SavedItem myListItem = SavedItem.fromCursor(cursor);
        viewHolder.textView.setText(myListItem.getArticle_Title());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.img_main_card_3);
            this.textView = itemView.findViewById(R.id.tv_card_main_3_title);
        }
    }
}
